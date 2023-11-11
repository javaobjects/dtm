package com.rabbiter.dm.service;

import com.rabbiter.dm.dao.NoticeMapper;
import com.rabbiter.dm.dto.Message;
import com.rabbiter.dm.entity.Notice;
import com.rabbiter.dm.socket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xianxian
 * @date 2022-02-04
 */
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private SystemUserService systemUserService;

    public List<Notice> listByUserId(Long uId) {
        return noticeMapper.listByUserId(uId);
    }

    public List<Notice> listBySendUser(Long uId) {
        return noticeMapper.listBySendUser(uId);
    }

    public void save(Notice notice) {
        noticeMapper.save(notice);
    }

    public Notice query(Long id) {
        return noticeMapper.query(id);
    }

    public Notice queryWithReceiver(Long id) {
        return noticeMapper.queryWithReceiver(id);
    }

    public void updateRead(Long uid, Long nid) {
        noticeMapper.updateRead(uid, nid);
    }

    public void saveRelevance(Long uid, Long nid) {
        noticeMapper.saveRelevance(uid, nid);
        Notice notice = noticeMapper.query(nid);
        Message message = new Message();
        message.setMessageBody(notice.getMsg());
        message.setType(1);
        message.setFrom(notice.getUser().getRealName());
        message.setTitle("新通知");
        WebSocket.sendMessage(uid, message, systemUserService);
    }

    public void saveRelevance(List<Long> uid, Long nid) {
        uid.forEach(l -> saveRelevance(l, nid));
    }

    public void deleteMyNotice(Long uid, Long nid) {
        noticeMapper.deleteMyNotice(uid, nid);
    }

    public void deleteNotice(Long id) {
        noticeMapper.deleteByNoticeId(id);
        noticeMapper.deleteNotice(id);
    }

    public int countByUserId(Long userId) {
        return noticeMapper.countByUserId(userId);
    }
}
