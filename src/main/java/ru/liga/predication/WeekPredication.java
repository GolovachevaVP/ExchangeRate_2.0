package ru.liga.predication;

import ru.liga.dto.DateAndCourse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeekPredication implements IPredication {
    public List<DateAndCourse> rate(List<DateAndCourse> course) {
        LocalDate date = LocalDate.now().plusDays(7);
        List<DateAndCourse> result = new ArrayList<>();
        while (!date.equals(course.get(0).getDate())) {
            double newCourse = 0;
            for (int i = 0; i < 7; i++) {
                newCourse += course.get(i).getCourse();
            }
            newCourse = newCourse / 7;
            DateAndCourse dateAndCourse = new DateAndCourse(newCourse, course.get(0).
                    getDate().plusDays(1));
            course.add(0, dateAndCourse);
        }
        for(int i=0; i<7; i++){
            result.add(new DateAndCourse(course.get(i).getCourse(), course.get(i).getDate()));
        }
        Collections.reverse(result);
        return result;

    }

}
