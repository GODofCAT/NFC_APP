package com.example.nfc_app.util;

import com.example.nfc_app.util.dto.FacilityContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalStorage {
    static public Map<String, Object> storage = new HashMap<>();

    public enum fragments {
      AuthorizeFragment,
      MainFragment,
      NfcFragment,
      DezinsecFragment,
      FerMonFragment,
        PovrChoiceFragment
    }

    static public List<FacilityContainer> facilityContainers = null;
}
