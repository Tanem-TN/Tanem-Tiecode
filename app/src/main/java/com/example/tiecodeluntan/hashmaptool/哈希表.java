package com.example.tiecodeluntan.hashmaptool;

import java.util.HashMap;

@SuppressWarnings("NonAsciiCharacters")
public class 哈希表 extends HashMap<Object, Object> {

    public void 添加项目(Object 键, Object 值) {
        this.put(键, 值);
    }

    public Object 取项目(Object 键) {
        return this.get(键);
    }

    public boolean 是否存在(Object 键) {
        return this.containsKey(键);
    }

    public int 长度() {
        return this.size();
    }

    public void 清空() {
        this.clear();
    }
}
