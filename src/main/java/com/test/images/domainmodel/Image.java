package com.test.images.domainmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "images_seq")
	@SequenceGenerator(name = "images_seq", sequenceName = "images_seq", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	private String partnerImageId;
	private String type;
	private String name;
	private String title;
	private String description;
	private String album;
	private String deleteHash;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	public Image() {
		super();
	}

	public Image(Long id, String partnerImageId, String type, String name, String title, String description,
			String album, String deleteHash, User user) {
		super();
		this.id = id;
		this.partnerImageId = partnerImageId;
		this.type = type;
		this.name = name;
		this.title = title;
		this.description = description;
		this.album = album;
		this.deleteHash = deleteHash;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getPartnerImageId() {
		return partnerImageId;
	}

	public void setPartnerImageId(String partnerImageId) {
		this.partnerImageId = partnerImageId;
	}

	public String getDeleteHash() {
		return deleteHash;
	}

	public void setDeleteHash(String deleteHash) {
		this.deleteHash = deleteHash;
	}

}
