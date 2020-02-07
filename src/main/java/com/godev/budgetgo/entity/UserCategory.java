package com.godev.budgetgo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "user_categories")
@Data
@NoArgsConstructor
public class UserCategory {

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

    public UserCategory cloneShallow() {
        UserCategory e = new UserCategory();
        e.setId(id);
        e.setUser(user);
        e.setCategory(category);
        e.setUsedForIncomes(isUsedForIncomes);
        e.setUsedForOutcomes(isUsedForOutcomes);
        return e;
    }
}
