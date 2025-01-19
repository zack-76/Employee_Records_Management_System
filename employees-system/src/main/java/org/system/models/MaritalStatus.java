package org.system.models;

public enum MaritalStatus {

    /**
     * SINGLE.
     */
    SINGLE("SINGLE"),

    /**
     * MARRIED.
     */
    MARRIED("MARRIED"),
    /**
     * DIVORCED
     */
    DIVORCED("DIVORCED"),
    /**
     * WIDOWED
     */
    WIDOWED("WIDOWED");




    /**
     * The Content.
     */
    private final String content;


    MaritalStatus(String content) {
        this.content = content;
    }


    /**
     * Code string.
     *
     * @return the string
     */
    public String code() {
        return name();
    }

    /**
     * Content string.
     *
     * @return the string
     */
    public String content() {
        return this.content;
    }
}
