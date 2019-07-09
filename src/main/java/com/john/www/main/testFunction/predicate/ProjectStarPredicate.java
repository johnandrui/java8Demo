package com.john.www.main.testFunction.predicate;

import com.john.www.main.testFunction.Project;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  14:07
 * @Modified By:
 */
public class ProjectStarPredicate implements ProjectPredicate {
    private Integer stars;

    public ProjectStarPredicate(Integer stars) {
        this.stars = stars;
    }

    @Override
    public boolean test(Project project) {
        return project.getStars() > stars;
    }
}
