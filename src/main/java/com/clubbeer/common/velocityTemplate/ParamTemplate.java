package com.clubbeer.common.velocityTemplate;

public class ParamTemplate {
    private String fieldKey;
    private String fieldVariable;

    public ParamTemplate(String fieldKey, String fieldVariable) {
        this.fieldKey = fieldKey;
        this.fieldVariable = fieldVariable;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getFieldVariable() {
        return fieldVariable;
    }

    public void setFieldVariable(String fieldVariable) {
        this.fieldVariable = fieldVariable;
    }
}
