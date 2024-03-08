package com.example.nfc_app.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nfc_app.R;
import com.example.nfc_app.connection.CheckConnection;
import com.example.nfc_app.db.Log;
import com.example.nfc_app.db.LogDb;
import com.example.nfc_app.util.LocalStorage;
import com.example.nfc_app.util.dto.AddLogRequestDto;
import com.example.nfc_app.util.dto.LogResponseDto;
import com.example.nfc_app.util.requsests.NetworkService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    private MainFragment mainFragment;
    private DeratizationFragment nfcFragment;
    private FerMonFragment ferMonFragment;
    private DezinsecFragment dezinsecFragment;
    private AuthorizeFragment authorizeFragment;

    private LogDb db;
    private List<Log> logForCheck;

    public static final String ErrorDetected = "NFC метка не найдена";
    public static final String WriteSuccess = "Запись прошла успешно!";
    public static final String ErrorWrite = "Ошибка записи";

    NfcAdapter adapter;
    PendingIntent pendingIntent;
    IntentFilter writingTagFilter[];
    boolean writeMode;
    Tag myTag;
    Context context;

    String[] parseText;
    byte[] currentTagId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        setTheme(R.style.Theme_MainFragment);

        db = Room.databaseBuilder(getApplicationContext(),LogDb.class,"populus-database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        LocalStorage.storage.put("db", db);

        Timer timer = new Timer();
        final int MILLISECONDS = 10000;
        timer.schedule(new CheckConnection(this, db), 0, MILLISECONDS);

        nfcFragment = new DeratizationFragment();
        LocalStorage.storage.put("nfcFragment", nfcFragment);

        dezinsecFragment = new DezinsecFragment();
        LocalStorage.storage.put("dezinsecFragment", dezinsecFragment);

        ferMonFragment = new FerMonFragment();
        LocalStorage.storage.put("ferMonFragment",ferMonFragment);

        mainFragment = new MainFragment();
        LocalStorage.storage.put("mainFragment", mainFragment);

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());

        context =  this;

        adapter = NfcAdapter.getDefaultAdapter(this);
        if (adapter == null) {
            Toast.makeText(context, "устройство не поддерживается", Toast.LENGTH_LONG).show();
        }


        readFromIntent(getIntent());

        //pendingIntent = PendingIntent.getActivity(context,0, new Intent(context, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pendingIntent = PendingIntent.getActivity(this,
                    0, new Intent(context, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);

        }else {
            pendingIntent = PendingIntent.getActivity(this,
                    0, new Intent(context, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_UPDATE_CURRENT);

        }
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        writingTagFilter = new IntentFilter[]{tagDetected};

        LocalStorage.storage.put("current fragment", LocalStorage.fragments.AuthorizeFragment);
        authorizeFragment = new AuthorizeFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, authorizeFragment)
                .commit();
    }

    private void writeLog(String log) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy", Locale.getDefault());
        String dateText = dateFormat.format(date);

        String fileName = dateText+".txt";

        String documentsPath = System.getenv("EXTERNAL_STORAGE") + "/Documents/Dezcenter_Logs";
        File directory = new File(documentsPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        LocalStorage.fragments fragment = (LocalStorage.fragments) LocalStorage.storage.get("current fragment");
        switch (fragment) {
            case NfcFragment:
                documentsPath = System.getenv("EXTERNAL_STORAGE") + "/Documents/Dezcenter_Logs/Deratization_Logs";
                break;
            case DezinsecFragment:
                documentsPath = System.getenv("EXTERNAL_STORAGE") + "/Documents/Dezcenter_Logs/Dezinsection_Logs";
                break;
            case FerMonFragment:
                documentsPath = System.getenv("EXTERNAL_STORAGE") + "/Documents/Dezcenter_Logs/FerMon_Logs";
                    break;
            default:
                return;
        }
        directory = new File(documentsPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }


        File file = new File(directory, fileName);

        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append(log+"\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {}
    }

    public void addNewLogToDb(String controlNum, String controlNumStatus, String tagNum, int companyId, int facilityId, int workId){
        if (controlNum.isEmpty()){
            Toast.makeText(context, ErrorWrite, Toast.LENGTH_LONG).show();
        }
        else {

            try {
                if (myTag == null || tagNum == null) {
                    Toast.makeText(context, ErrorDetected, Toast.LENGTH_LONG).show();
                } else {
                    Date date = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    String dateText = dateFormat.format(date);
                    String uuid = UUID.randomUUID().toString();
                    Log log = new Log(tagNum, dateText, facilityId, Integer.valueOf(controlNum), controlNumStatus, workId, companyId, uuid);
                    db.getLogDao().insert(log);

                    logForCheck = db.getLogDao().getLogs();
                    for (Log currLog : logForCheck) {
                        System.out.println(currLog.getUuid() + "\n");
                    }
                }
            } catch (Exception e) {
                Toast.makeText(context, ErrorWrite, Toast.LENGTH_LONG).show();
            }
        }
    }

    private String hexIdToStringId(byte[] hexId){
        String stringId = new String();
        String x;
        for (int i = 0; i < hexId.length - 1; i++) {
             x= Integer.toHexString(((int) hexId[i] & 0xff));
            if (x.length() == 1) {
                x = '0' + x;
            }
            stringId += x + ':';
        }

        x= Integer.toHexString(((int) hexId[hexId.length - 1] & 0xff));
        if (x.length() == 1) {
            x = '0' + x;
        }
        stringId += x;

        return stringId;
    }

    public void writeTag(String text, String controllNum, String wirehouse){
        try{
            if (myTag == null) {
                Toast.makeText(context, ErrorDetected, Toast.LENGTH_LONG).show();
            } else {
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy", Locale.getDefault());
                String dateText = dateFormat.format(date);

                if (!controllNum.equals("")) {
                    write(dateText + "-" +  wirehouse + "-#" + controllNum + "-" + text, myTag);
                    writeLog(hexIdToStringId(currentTagId).toUpperCase() + "-" + dateText + "-" +  wirehouse + "-#" +  controllNum + "-" + text );
                }
                else{
                    write(dateText + "-" +  wirehouse + "-" + parseText[2] + "-" + text, myTag);
                    writeLog(hexIdToStringId(currentTagId).toUpperCase() + "-" + dateText + "-" +  wirehouse + "-" + parseText[2] + "-" + text );
                }
                    Toast.makeText(context, WriteSuccess, Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            Toast.makeText(context, ErrorWrite, Toast.LENGTH_LONG).show();
        }
    }

    public String getCurrentTagId(){
        if (currentTagId == null){
            return null;
        }
        return hexIdToStringId(currentTagId).toUpperCase();
    }

    private void readFromIntent(Intent intent){
        String action = intent.getAction();
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)||NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)||NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)){
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs = null;
            currentTagId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
            if (rawMsgs != null){
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i <rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
            buildTagViews(msgs);
        }
    }

    @SuppressLint("LongLogTag")
    private void buildTagViews(NdefMessage[] msgs){
        TextView viewTag = null;
        LocalStorage.fragments fragment = (LocalStorage.fragments) LocalStorage.storage.get("current fragment");
        switch (fragment){
            case NfcFragment:
                viewTag = nfcFragment.textViewTag;
                break;
            case DezinsecFragment:
                viewTag = dezinsecFragment.textViewTag;
                break;
            case FerMonFragment:
                viewTag = ferMonFragment.textViewTag;
                break;
            default:
                return;
        }

        if (msgs == null || msgs.length == 0){
            viewTag.setText("null");
            return;
        }

        String text = "";
        byte[] payload = msgs[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8":"UTF-16";
        int languageCodeLength = payload[0] & 0063;

        try {
            text = new String(payload, languageCodeLength+1, payload.length - languageCodeLength - 1, textEncoding);
            parseText = text.split("-");
        } catch (UnsupportedEncodingException e) {
            android.util.Log.e("кодировка не поддерживается", e.toString());
        }

        viewTag.setText(text);

    }

    private void write(String text, Tag tag) throws IOException, FormatException {
        NdefRecord[] records = {createRecord(text)};
        NdefMessage message = new NdefMessage(records);

        Ndef ndef = Ndef.get(tag);
        ndef.connect();
        ndef.writeNdefMessage(message);
        ndef.close();
    }

    private NdefRecord createRecord(String text) throws UnsupportedEncodingException{
        String lang = "en";
        byte[] textBytes = text.getBytes();
        byte[] langBytes = lang.getBytes("US-ASCII");
        int langLength =
                langBytes.length;
        int textLength = textBytes.length;
        byte[] payLoad = new byte[1+langLength+textLength];

        payLoad[0] = (byte)langLength;

        System.arraycopy(langBytes,0,payLoad,1,langLength);
        System.arraycopy(textBytes,0,payLoad,1+langLength,textLength);

        NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,NdefRecord.RTD_TEXT,new byte[0],payLoad);

        return recordNFC;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        readFromIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        writeModeOff();
    }

    @Override
    public void onResume() {
        super.onResume();
        writeModeOn();
    }

    private void writeModeOn(){
        writeMode = true;
        adapter.enableForegroundDispatch(this,pendingIntent,writingTagFilter, null);
    }

    private void writeModeOff(){
        writeMode = false;
        adapter.disableForegroundDispatch(this);
    }
}