/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdmv.repositories.impl;

import com.pdmv.pojo.SchoolYear;
import com.pdmv.repositories.SchoolYearRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author phamdominhvuong
 */
@Repository
@Transactional
public class SchoolYearRepositoryImpl implements SchoolYearRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addOrUpdate(SchoolYear schoolYear) {
        Session s = this.factory.getObject().getCurrentSession();
        if (schoolYear.getId() == null) {
            s.save(schoolYear);
        } else {
            s.update(schoolYear);
        }
    }

    @Override
    public SchoolYear getSchoolYearById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(SchoolYear.class, id);
    }

    @Override
    public List<SchoolYear> getSchoolYears() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SchoolYear> query = builder.createQuery(SchoolYear.class);
        Root<SchoolYear> root = query.from(SchoolYear.class);

        query.orderBy(builder.desc(root.get("id"))); 

        Query<SchoolYear> q = session.createQuery(query);
        return q.getResultList();
    }
    
}
