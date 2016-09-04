package cn.stt.test;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ������ʵ����
 * Created by stt on 2016/9/3.
 */
@Data
public class Member implements Comparable<Member>{

    // ��ʽ��������
    private static final SimpleDateFormat MY_SDF = new SimpleDateFormat(
            "yyyy-MM-dd");

    // ��������
    private int id;
    private String username;
    private int level;
    private Date birthday;

    public Member(){}

    // ���캯��
    public Member(int id, String username, int level, String birthday) throws Exception {
        this.id = id;
        this.username = username;
        this.level = level;
        this.birthday = new Date(MY_SDF.parse(birthday).getTime());
    }

    // ע�⣺���ʹ��MySortList�࣬��˷���������Ҫ��
    // ��Ϊ�˷������ṩ��Collections.sort����ʹ�õġ�
    public int compareTo(Member o) {
        // ֻ�ܶ�һ���ֶ����Ƚϣ��������������ıȽϾ�ʵ�ֲ��˰�ָ���ֶ������ˡ�
        return this.getUsername().compareTo(o.getUsername());
    }
}
