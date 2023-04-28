package cdu.nls.app.service;

import cdu.nls.app.model.Msg;

import java.util.List;
import java.util.Map;

public interface MsgService {

    //分页获取所有留言
    List<Msg> findByPage(int page, int pageSize, int reStatus);
    List<Msg> findByPage(String sPage, String sPageSize, int reStatus);
    List<Msg> findByPage(String sPage, int reStatus);

    //根据id获取某一条留言
    Msg get(String sid);
    Msg get(int id);

    //获取留言总数
    int count(int reStatus);

    //添加留言
    boolean add(Msg msg);
    //回复留言
    boolean reply(Msg msg);
    //删除留言
    boolean del(String sid);



}
