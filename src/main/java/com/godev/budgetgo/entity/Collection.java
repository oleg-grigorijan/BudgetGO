package com.godev.budgetgo.entity;

import javax.persistence.*;

@Entity
@Table(name = "collections")
public class Collection {
    @EmbeddedId
    private UserCategoryKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;


    public UserCategoryKey getId() {
        return id;
    }

    public void setId(UserCategoryKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
