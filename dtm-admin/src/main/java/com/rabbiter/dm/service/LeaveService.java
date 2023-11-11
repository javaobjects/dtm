package com.rabbiter.dm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbiter.dm.dao.LeaveMapper;
import com.rabbiter.dm.dto.ListQuery;
import com.rabbiter.dm.entity.Leave;
import com.rabbiter.dm.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author xianxian
 * @date 2022-03-10
 */
@Service
public class LeaveService {
    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private StudentService studentService;

    public void save(Leave leave) {
        leaveMapper.save(leave);
        Student student = new Student();
        student.setIsLeave(true);
        student.setId(leave.getStudentId());
        studentService.updateLeave(student);
    }

    public void update(Long id) {
        leaveMapper.update(id);
        Leave leave = query(id);
        Student student = new Student();
        student.setIsLeave(false);
        student.setId(leave.getStudentId());
        studentService.updateLeave(student);
    }

    public PageInfo<Leave> list(ListQuery<Leave> listQuery, Long bid) {
        Leave leave = listQuery.getEntity();
        List<Long> bIds = buildingService.getIdsByParentId(bid);
        PageHelper.startPage(listQuery.getPage(), listQuery.getRows());
        List<Leave> list = leaveMapper.list(leave, bIds);
        return new PageInfo<>(list);
    }

    public Leave query(Long id) {
        return leaveMapper.query(id);
    }

    public void delete(Long id) {
        Leave leave = query(id);
        if (leave != null) {
            leaveMapper.delete(id);
            Student student = new Student();
            student.setIsLeave(false);
            student.setId(leave.getStudentId());
            studentService.updateLeave(student);
        }
    }

    public void saveOrUpdate(Leave leave) {
        if(ObjectUtils.isEmpty(leave.getId())) {
            leaveMapper.save(leave);
            Student student = new Student();
            student.setIsLeave(true);
            student.setId(leave.getStudentId());
            studentService.updateLeave(student);
        } else {
            leaveMapper.updateEntity(leave);
        }
    }
}
