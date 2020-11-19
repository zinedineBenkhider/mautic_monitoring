package com.example.mautic_mobile.api.entities;

import java.util.List;

public class FormsResponse {
    private int total;
    private List<Form> forms;

    public FormsResponse(int total, List<Form> forms) {
        this.total = total;
        this.forms = forms;
    }

    public List<Form> getFormList() {
        return forms;
    }

    public void setFormList(List<Form> formList) {
        this.forms = formList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
