package com.example.demo.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.util.HibernateUtil;

@Service
public class StudentService {

	public Student create(Student student) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student objects
			session.save(student);
			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return student;
	}

	public Student getFromDb(int studentId) {
		Transaction transaction = null;
		Student student = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student objects
			student = session.byId(Student.class).getReference(studentId);
			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return student;
	}

	public Student update(int id, Student update) {
		Transaction transaction = null;
		Student student = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student objects
			student = session.byId(Student.class).getReference(id);
			
			
			if(update.getFirstName() != null) {
				student.setFirstName(update.getFirstName());
			}
			if(update.getEmail() != null) {
				student.setLastName(update.getLastName());
			}
			if(update.getLastName() != null) {
				student.setEmail(update.getEmail());
			}
			
			session.update(student);
			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		System.out.println(student);
		return student;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		Student student = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student objects
			student = session.byId(Student.class).getReference(id);
			
			session.delete(student);
			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

}
