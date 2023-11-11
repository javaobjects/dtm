package com.rabbiter.dm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbiter.dm.dao.StudentMapper;
import com.rabbiter.dm.dto.ListQuery;
import com.rabbiter.dm.entity.Student;
import com.rabbiter.dm.exception.HttpException;
import com.rabbiter.dm.utils.HttpCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author xianxian
 * @date 2022-01-22
 */
@Service
public class StudentService {
    private final StudentMapper studentMapper;
    private final BuildingService buildingService;
    @Autowired
    private FacultyService facultyService;
    private final RoomService roomService;

    public StudentService(StudentMapper studentMapper, BuildingService buildingService, RoomService roomService) {
        this.studentMapper = studentMapper;
        this.buildingService = buildingService;
        this.roomService = roomService;
    }

    public void saveOrUpdate(Student student) {
        int maxCapacity = roomService.getMaxCapacity(student.getRoomId());
        int count = studentMapper.countByRoomId(student.getRoomId());
        if (student.getId() == null) {
            if (count >= maxCapacity) {
                throw new HttpException(HttpCode.FAILED, "所选寝室已满");
            }
            studentMapper.save(student);
        } else {
            Student oldData = studentMapper.query(student.getId()).get();
            if (!oldData.getRoomId().equals(student.getRoomId())) {
                if (count >= maxCapacity) {
                    throw new HttpException(HttpCode.FAILED, "所选寝室已满");
                }
            }
            studentMapper.update(student);
        }
    }

    public void delete(Long id) {
        studentMapper.delete(id);
    }

    public Optional<Student> query(Long id) {
        return studentMapper.query(id);
    }

    public List<Student> list(String name, Long buildingId) {
        List<Long> buildingIds = buildingService.getIdsByParentId(buildingId);
        Student student = new Student();
        student.setName(name);
        return studentMapper.list(student, null, buildingIds);
    }

    public PageInfo<Student> list(ListQuery<Student> listQuery, Long buildingId) {
        List<Long> facultyIds = null;
        Student student = listQuery.getEntity();
        if (student.getFacultyId() != null) {
            facultyIds = facultyService.getIdsByParentId(student.getFacultyId());
        }
        List<Long> buildingIds = buildingService.getIdsByParentId(buildingId);
        PageHelper.startPage(listQuery.getPage(), listQuery.getRows());
        List<Student> studentList = studentMapper.list(student, facultyIds, buildingIds);
        return new PageInfo<>(studentList);
    }

    public int countByRoomId(Long rid) {
        return studentMapper.countByRoomId(rid);
    }

    public void updateLeave(Student student) {
        studentMapper.updateLeave(student);
    }
}
