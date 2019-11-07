package com.godev.budgetgo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "storages_relations")
public class StorageRelations implements Cloneable {
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

    @ManyToOne
    @JoinColumn(name = "inviter_id", nullable = false)
    private User inviter;

    @Column(name = "is_invitation", nullable = false)
    private boolean invitation;

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

    public User getInviter() {
        return inviter;
    }

    public void setInviter(User inviter) {
        this.inviter = inviter;
    }

    public boolean isInvitation() {
        return invitation;
    }

    public void setInvitation(boolean invitation) {
        this.invitation = invitation;
    }

    /**
     * @return Shallow clone of instance
     */
    @Override
    public StorageRelations clone() {
        try {
            return (StorageRelations) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StorageRelations)) return false;
        StorageRelations relations = (StorageRelations) o;
        return includedInUserStatistics == relations.includedInUserStatistics &&
                invitation == relations.invitation &&
                id.equals(relations.id) &&
                user.equals(relations.user) &&
                storage.equals(relations.storage) &&
                userRole == relations.userRole &&
                inviter.equals(relations.inviter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, storage, includedInUserStatistics, userRole, inviter, invitation);
    }

    @Override
    public String toString() {
        return "StorageRelations{" +
                "id=" + id +
                ", user=" + user +
                ", storage=" + storage +
                ", includedInUserStatistics=" + includedInUserStatistics +
                ", userRole=" + userRole +
                ", inviter=" + inviter +
                ", invitation=" + invitation +
                '}';
    }
}
