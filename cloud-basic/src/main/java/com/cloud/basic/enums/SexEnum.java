package com.cloud.basic.enums;

/**
 * @Description: 性别枚举
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
public enum SexEnum {
    /**
     * 男
     */
    MAN(0, "男"),
    /**
     * 女
     */
    WOMAN(1, "女");

    private int id;

    private String name;

    private SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 状态转换 toId
     *
     * @param value
     * @return
     */
    public static int convertToId(String value) {
        if (value.trim().equals(MAN.name)) {
            return MAN.getId();
        } else {
            return WOMAN.getId();
        }
    }

    /**
     * 状态转换 toName
     *
     * @param id
     * @return
     */
    public static String convertToName(int id) {
        if (id == MAN.getId()) {
            return MAN.name;
        } else {
            return WOMAN.name;
        }
    }
}
