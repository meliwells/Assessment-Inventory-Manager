package com.assessment.inventoryManager.ui;

public enum MenuOption {
    ADD_PRODUCT("Add Product"),
    VIEW_PRODUCTS("View Products"),
    SEARCH_PRODUCT("Search Product"),
    UPDATE_PRODUCT("Update Product"),
    DELETE_PRODUCT("Delete Product"),
    EXIT("Exit");

    private String message;

    MenuOption(String name) {
        this.message = name;
    }

    public String getMessage() {
        return message;
    }
}

