package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Configuration;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class MessageService {

	// Managed repository

	@Autowired
	private MessageRepository messageRepository;

	// Supporting services
	@Autowired
	private ActorService actorService;

	@Autowired
	private FolderService folderService;

	// Constructors

	public MessageService() {
		super();
	}

	// Simple CRUD methods

	public Message create() {
		Message message;
		message = new Message();
		Actor sender = this.actorService.findByPrincipal();
		Date moment;
		Collection<Actor> recipient;
		Folder folder = new Folder();
		recipient = new ArrayList<Actor>();
		moment = new Date(System.currentTimeMillis() - 1);
		message.setSender(sender);
		message.setMoment(moment);
		message.setRecipient(recipient);
		message.setSpam(false);
		message.setFolder(folder);
		return message;
	}

	public Collection<Message> findAll() {
		Collection<Message> res;
		res = this.messageRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Message findOne(int message) {
		Message res;
		res = this.messageRepository.findOne(message);
		Assert.notNull(res);
		return res;
	}

	public Message save(Message message) {
		Message res;

		Actor sender = actorService.findByPrincipal();

		Folder f = folderService.findFolderName("Out Box", sender.getId());


		Collection<Message> msgs = new ArrayList<Message>();
		msgs.addAll(f.getMessages());
		msgs.add(message);

		f.setMessages(msgs);
		
		res = this.messageRepository.save(message);
		
		Collection<Actor> recipient = res.getRecipient();
		
		res.setFolder(f);
		
		for (Actor a : recipient) {
			if(message.getSpam()==false){
				Message res2;
				res2 = this.messageRepository.save(message);
				
				Collection<Message> messages = new ArrayList<Message>();
				
				Folder inbox = folderService.findFolderName("In Box", a.getId());
				res2.setFolder(inbox);
				messages.addAll(inbox.getMessages());
				messages.add(res);
				inbox.setMessages(messages);
			} else {
				Message res2;
				res2 = this.messageRepository.save(message);
				
				Collection<Message> messages = new ArrayList<Message>();
				
				Folder inbox = folderService.findFolderName("Spam", a.getId());
				res2.setFolder(inbox);
				messages.addAll(inbox.getMessages());
				messages.add(res);
				inbox.setMessages(messages);
				
				sender.setSuspicious(true);
			}
		}

		return res;
	}

	public void delete(Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(this.messageRepository.exists(message.getId()));
		
		Actor actor = actorService.findByPrincipal();
		
		Folder f = folderService.findFolderName("Trash", actor.getId());
		
		if(!f.getMessages().contains(message)){
			Message newMessage;

			newMessage = message;
			newMessage.setFolder(f);
			this.messageRepository.save(newMessage);
		} else {
			this.messageRepository.delete(message);
		}
	}
	
	public void moveMessage(Message message, Folder folder){
		Actor sender = actorService.findByPrincipal();
		
		Folder f = folderService.findFolderName(folder.getName(), sender.getId());
		
		Message newMessage;

		newMessage = message;
		newMessage.setFolder(f);
		this.messageRepository.save(newMessage);
		
	}

	// Other business methods
	
	public Message Notification(Message message) {
		Message res;

		Actor sender = actorService.findByPrincipal();

		Folder f = folderService.findFolderName("Out Box", sender.getId());

		Collection<Message> msgs = new ArrayList<Message>();
		msgs.addAll(f.getMessages());
		msgs.add(message);

		f.setMessages(msgs);

		res = this.messageRepository.save(message);
		
		Collection<Actor> recipient = res.getRecipient();
		
		res.setFolder(f);
		
		for (Actor a : recipient) {
			Message res2;
			res2 = this.messageRepository.save(message);
			
			Collection<Message> messages = new ArrayList<Message>();
			
			Folder inbox = folderService.findFolderName("Notification", a.getId());
			res2.setFolder(inbox);
			messages.addAll(inbox.getMessages());
			messages.add(res);
			inbox.setMessages(messages);
		}

		return res;
	}
	
	public void messageToSpamFolder(Message message, Configuration configuration){
		Collection<String> spamWords = configuration.getSpamWords();
		
		for(String sM: spamWords){
			String s = sM.toLowerCase();
			if(message.getBody().toLowerCase().contains(s) || message.getSubject().toLowerCase().contains(s)){
				message.setSpam(true);
			}
		}
	}

}
