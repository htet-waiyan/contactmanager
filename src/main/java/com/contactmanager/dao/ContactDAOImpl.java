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
	public User addContact(Contact contact,Integer id){
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, id);
		Query groupQuery=session.getNamedQuery("Group.GetExistingGroupName");
		groupQuery.setParameter("desc", contact.getGroup().getDescription().trim());
		
		if(groupQuery.list().size()>0){
			contact.setGroup((Group)groupQuery.list().get(0));
		}
		
		Query typeQuery=session.getNamedQuery("ContactType.GetExistingType");
		
		for(ContactNumber contNum:contact.getContactNumberSets()){
			typeQuery.setParameter("desc", contNum.getContactType().getDescription().trim());
			
			if(typeQuery.list().size()>0){
				contNum.setContactType((ContactType)typeQuery.list().get(0));
			}
		}
		
		user.getContactList().add(contact);
		
		System.out.println("before inserting....");
		for(ContactNumber c:user.getContactList().get(0).getContactNumberSets()){
			System.out.println(c);
		}
		session.update(user);
		return user;
	}

	@Override
	public List<Contact> getAllContacts(Integer id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.getNamedQuery("User.RetrieveContacts");
		query.setParameter("userID", id);
		query.setParameter("deleted", false);
		
		System.out.println("Retrieving Contacts....");
		List<Contact> contactList=query.list();
		return contactList;
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
	public ContactType getContactType(String desc){
		Session session=sessionFactory.getCurrentSession();
		Query query=session.getNamedQuery("ContactType.GetExistingType");
		query.setParameter("desc", desc);
		
		List list=query.list();
		
		if(list.size()>0)
			return (ContactType) list.get(0);
		else
			return new ContactType(desc);
	}

	@Override
	public User editContact(Contact contact,Integer userID) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		
		
		Query query=session.getNamedQuery("Contact.DeleteAllNumbers");
		query.setParameter("id", contact.getContactID());
		
		query.executeUpdate();
		
		System.out.println("Resetting Numbers");
		
		query=session.getNamedQuery("Group.GetExistingGroupName");
		query.setParameter("desc", contact.getGroup().getDescription());
		
		//if the group is already existed. then re-assign the group id
		System.out.println("Group Info : "+contact.getGroup().getGroupId()+" "+contact.getGroup().getDescription());
		if(query.list().size()>0){
			Group group=(Group)query.list().get(0);
			System.out.println("Resetting group : "+group.getGroupId()+" "+group.getDescription());
			System.out.println("The same group? : "+group.equals(contact.getGroup()));
			
			if(contact.getGroup().getGroupId()!=group.getGroupId())
				contact.setGroup(group);
		}
		else{
			//inserting a new row....
			contact.getGroup().setGroupId(null);
		}
		
		session.clear();
		session.saveOrUpdate(contact);
		
		User user=(User) session.get(User.class, userID);
		Hibernate.initialize(user.getContactList());
		
		return user;
	}
	
	
	
}
