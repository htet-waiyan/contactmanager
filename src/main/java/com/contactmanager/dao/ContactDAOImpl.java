package com.contactmanager.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.contactmanager.model.Contact;
import com.contactmanager.model.ContactNumber;
import com.contactmanager.model.ContactType;
import com.contactmanager.model.Group;
import com.contactmanager.model.User;

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
		dbQuery.setParameter("fName", query);
		dbQuery.setParameter("lName", query);
		
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
		
		Contact contact=(Contact)session.get(Contact.class, id);
		session.flush();
		return contact;
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

	@Override
	public User editContact(Contact contact,Integer userID) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		
		
		Query query=session.getNamedQuery("Contact.DeleteAllNumbers");
		query.setParameter("id", contact.getContactID());
		
		query.executeUpdate();
		
		session.flush();
		
		System.out.println(session.contains(contact));
		
		System.out.println("Resetting Numbers");
		
		query=session.getNamedQuery("Group.GetExistingGroupName");
		query.setParameter("desc", contact.getGroup().getDescription());
		
		//if the group is already existed. then re-assign the group id
		if(query.list().size()>0){
			Group group=(Group)query.list().get(0);
			contact.getGroup().setGroupId(group.getGroupId());
		}
		
		for(ContactNumber number:contact.getContactNumberSets()){
			query=session.getNamedQuery("ContactType.GetExistingType");
			query.setParameter("desc", number.getContactType().getDescription());
			
			//if the type is already existed. then re-assign the type id
			System.out.println("Editing Numbers : "+number.getKey()+" "+number.getNumber());
			
			if(query.list().size()>0){
				ContactType type=(ContactType) query.list().get(0);
				number.getContactType().setSerial(type.getSerial());
			}
			//
		}
		session.merge(contact);
		
		User user=(User) session.get(User.class, userID);
		Hibernate.initialize(user.getContactList());
		
		return user;
	}
	
	
	
}
