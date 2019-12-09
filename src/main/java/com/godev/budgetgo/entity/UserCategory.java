package com.godev.budgetgo.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_categories")
public class UserCategory implements Cloneable {

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

    @Column(name = "is_used_for_incomes", nullable = false)
    private boolean isUsedForIncomes;

    @Column(name = "is_used_for_outcomes", nullable = false)
    private boolean isUsedForOutcomes;

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

    public boolean isUsedForIncomes() {
        return isUsedForIncomes;
    }

    public void setUsedForIncomes(boolean usedForIncomes) {
        isUsedForIncomes = usedForIncomes;
    }

    public boolean isUsedForOutcomes() {
        return isUsedForOutcomes;
    }

    public void setUsedForOutcomes(boolean usedForOutcomes) {
        isUsedForOutcomes = usedForOutcomes;
    }

    /**
     * @return Shallow clone of instance
     */
    @Override
    public UserCategory clone() {
        try {
            return (UserCategory) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
