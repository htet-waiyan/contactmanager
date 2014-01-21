package com.contactmanager.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.contactmanager.model.Contact;
import com.contactmanager.model.ContactType;
import com.contactmanager.model.Group;

@Repository
public class ContactDAOImpl implements ContactDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addContact(Contact contact) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.save(contact);
	}

	@Override
	public List<Contact> getAllContacts(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> getContactsUnder(String groupName, Integer userID) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.getNamedQuery("ListContactsBy");
		query.setParameter("userID", userID);
		query.setParameter("desc", groupName.trim());
		query.setParameter("deleted", false);
		
		List<Contact> contactList=query.list();
		return contactList;
	}

	@Override
	public List<Contact> searchContacts(String query, Integer userID) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query dbQuery=session.getNamedQuery("Contact.SearchContacts");
		dbQuery.setParameter("userID", userID);
		dbQuery.setParameter("fName", "%"+query+"%");
		dbQuery.setParameter("lName", "%"+query+"%");
		
		return dbQuery.list();
	}

	@Override
	@Transactional
	public void deleteContacts(String[] contactIDs) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Contact contact=null;
		
		for (String id : contactIDs) {
			contact=(Contact)session.get(Contact.class, Integer.parseInt(id.trim()));
			
			contact.setDeleted(true);
			session.merge(contact);
		}
		
	}

	@Override
	public Contact getContactDetailByID(Integer id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		
		return (Contact)session.get(Contact.class, id);
	}

	@Override
	public void moveContactsTo(String[] contIDs, String moveTo) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		
		for(String id:contIDs){
			Contact cont=(Contact) session.get(Contact.class, Integer.parseInt(id.trim()));
			Query query=session.getNamedQuery("Group.GetExistingGroupName").setParameter("desc", moveTo.trim());
			Group group=null;
			
			if(query.list().size()>0)
				group=(Group)query.list().get(0);
			else
				group=new Group(moveTo);
			
			cont.setGroup(group);
			
			session.saveOrUpdate(cont);
		}
	}
	
	
	
}
