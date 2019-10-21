package com.godev.budgetgo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_storage_relations")
public class UserStorageRelations {
    @EmbeddedId
    private UserStorageKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("storageId")
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @Column(name = "is_included_in_user_statistics", nullable = false)
    private boolean includedInUserStatistics;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStorageRole userRole;

    public UserStorageKey getId() {
        return id;
    }

    public void setId(UserStorageKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public boolean isIncludedInUserStatistics() {
        return includedInUserStatistics;
    }

    public void setIncludedInUserStatistics(boolean includedInUserBalance) {
        this.includedInUserStatistics = includedInUserBalance;
    }

    public UserStorageRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserStorageRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStorageRelations)) return false;
        UserStorageRelations that = (UserStorageRelations) o;
        return includedInUserStatistics == that.includedInUserStatistics &&
                id.equals(that.id) &&
                user.equals(that.user) &&
                storage.equals(that.storage) &&
                userRole == that.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, storage, includedInUserStatistics, userRole);
    }

    @Override
    public String toString() {
        return "UserStorageRelations{" +
                "id=" + id +
                ", user=" + user +
                ", storage=" + storage +
                ", includedInUserStatistics=" + includedInUserStatistics +
                ", userRole=" + userRole +
                '}';
    }
}
