package com.example.qldt_ptit_android_app_summer_2023.model

class FilterRequest {
    private var filter: MutableMap<String, Any?> = mutableMapOf()
    private var additional: MutableMap<String, Any?> = mutableMapOf()
    fun addFilter(key: String, value: Any?){
        filter.put(key, value)
    }
    fun addAdditional(key: String, value: Any?){
        additional.put(key, value)
    }
}
