package com.godev.budgetgo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "storages_relations")
@Data
@NoArgsConstructor
public class StorageRelations {

    @EmbeddedId
    private UserStorageKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public StorageRelations cloneShallow() {
        StorageRelations e = new StorageRelations();
        e.setId(id);
        e.setUser(user);
        e.setStorage(storage);
        e.setIncludedInUserStatistics(includedInUserStatistics);
        e.setUserRole(userRole);
        e.setInviter(inviter);
        e.setInvitation(invitation);
        return e;
    }
}
