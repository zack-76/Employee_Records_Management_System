package org.system.models;

public enum EmploymentStatus {


    /**
     * ACTIVE.
     */
    ACTIVE("ACTIVE"),

    /**
     * INACTIVE.
     */
    INACTIVE("INACTIVE");




    /**
     * The Content.
     */
    private final String content;


    EmploymentStatus(String content) {
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
