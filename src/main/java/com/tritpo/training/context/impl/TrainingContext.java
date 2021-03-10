package com.tritpo.training.context.impl;

import com.tritpo.training.context.ApplicationContext;
import com.tritpo.training.dao.impl.CourseDaoImpl;
import com.tritpo.training.dao.impl.CoursesScheduleDao;
import com.tritpo.training.dao.impl.ScheduleDaoImpl;
import com.tritpo.training.dao.impl.UserDaoImpl;
import com.tritpo.training.domain.*;
import com.tritpo.training.exception.ContextException;
import com.tritpo.training.pool.ConnectionPool;
import com.tritpo.training.specification.impl.FindAll;
import com.tritpo.training.specification.impl.FindById;
import com.tritpo.training.specification.impl.coursesSchedule.FindByCourseId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class   TrainingContext implements ApplicationContext {

    private static TrainingContext context;

    public static TrainingContext getInstance() {
        if (context == null) {
            context = new TrainingContext();
        }

        return context;
    }

    private static final Logger logger = LogManager.getLogger(TrainingContext.class);
    private TrainingContext() {
        ConnectionPool.getInstance();
        init();
    }

    private Collection<Course> courses = new ArrayList<>();
    private Collection<User> users = new ArrayList<>();

    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.getName().equals(Course.class.getName()))
            return (Collection<T>) courses;
        if (tClass.getName().equals(User.class.getName()))
            return (Collection<T>) users;
        return null;
    }

    /**
     * fill in cache when application starts
     */
    @Override
    public void init() {
        try {
            fillInCollections();
            for (Course course : courses) {
                fillCourseSchedule(course);
            }
            logger.info("Cache initialised successfully");
        } catch (ContextException e) {
            e.printStackTrace();
            logger.fatal("Cant fill in cache");
            System.exit(1);
        }

    }

    private void fillCourseSchedule(Course course) throws ContextException {
        List<ManyToManyEntity> list = CoursesScheduleDao.getInstance().queryAll(new FindByCourseId(course.getId()));
        if (!list.isEmpty()) {
            Set<Schedule> scheduleSet = getSchedulesByCourse(list);
            course.setScheduleSet(scheduleSet);
        } else {
            throw new ContextException("Cant get schedules from CoursesSchedule table");
        }
    }

    private Set<Schedule> getSchedulesByCourse(List<ManyToManyEntity> list) throws ContextException{
        Optional<Schedule> schedule1 = ScheduleDaoImpl.getInstance().query(new FindById(list.get(0).getFirstID()));
        Optional<Schedule>  schedule2 = ScheduleDaoImpl.getInstance().query(new FindById(list.get(1).getFirstID()));
        Optional<Schedule>  schedule3 = ScheduleDaoImpl.getInstance().query(new FindById(list.get(2).getFirstID()));
        if(schedule1.isPresent() && schedule2.isPresent() && schedule3.isPresent() ) {
            Set<Schedule> scheduleSet = new HashSet<>();
            scheduleSet.add(schedule1.get());
            scheduleSet.add(schedule2.get());
            scheduleSet.add(schedule3.get());
            return scheduleSet;
        }
        throw  new ContextException("Cant find schedule in schedules table");
    }

    private void fillInCollections() throws ContextException {
        courses = CourseDaoImpl.getInstance().queryAll(new FindAll());
        users = UserDaoImpl.getInstance().queryAll(new FindAll());
        if (courses.isEmpty() || users.isEmpty()) {
            throw new ContextException("Failed");
        }
    }

    public Course findById(int id) {
         return  courses.stream()
                 .filter(course1 -> course1.getId() == id)
                 .findFirst().get();
    }
}
