package com.iyungu.www.main.testFunction.predicate;

import com.iyungu.www.main.testFunction.Project;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  14:08
 * @Modified By:
 */
public class ProjectLanguagePredicate implements ProjectPredicate {
    private String language;

    public ProjectLanguagePredicate(String language) {
        this.language = language;
    }

    @Override
    public boolean test(Project project) {
        return language.equals(project.getLanguage());
    }
}
