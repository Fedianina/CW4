package tables;


import db.base.ConnectionSettings;
import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Help.ParentClass;

@Entity
@Table
public class Groups extends ParentClass {
    @JoinColumn
    @ManyToMany
    @JoinTable(name="alpenist_groups")
    private List <Alpenist> alpenists;

    @ManyToOne
    private Mountain mountain;

    @Column(nullable = false)
    private boolean recruitmentStatus = true;

    @Column(nullable = false)
    private int maximumParticipants = 0;

    @Column(nullable = false)
    private LocalDateTime dateOfAscent;


    public Groups() {
    }

    public Groups(Mountain mountain, int maximumParticipants, LocalDateTime dateOfAscent) {
        this.alpenists=new ArrayList<>();
        setMountain(mountain);
        setMaximumParticipants(maximumParticipants);
        setDateOfAscent(dateOfAscent);
    }

    public List<Alpenist> getAlpenists() {
        return alpenists;
    }

    public void setAlpenists(List<Alpenist> alpenists) {
        this.alpenists = alpenists;
    }

    public Mountain getMountain() {
        return mountain;
    }

    public void setMountain(Mountain mountain) {
        this.mountain = mountain;
    }

    public boolean isRecruitmentStatus() {
        return recruitmentStatus;
    }

    public void setRecruitmentStatus(boolean recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public int getMaximumParticipants() {
        return maximumParticipants;
    }

    public void setMaximumParticipants(int maximumParticipants) {
        this.maximumParticipants = maximumParticipants;
    }

    public LocalDateTime getDateOfAscent() {
        return dateOfAscent;
    }

    public void setDateOfAscent(LocalDateTime dateOfAscent) {
        this.dateOfAscent = dateOfAscent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Groups groups = (Groups) o;

        if (recruitmentStatus != groups.recruitmentStatus) return false;
        if (maximumParticipants != groups.maximumParticipants) return false;
        if (!Objects.equals(alpenists, groups.alpenists)) return false;
        if (!Objects.equals(mountain, groups.mountain)) return false;
        return Objects.equals(dateOfAscent, groups.dateOfAscent);
    }

    @Override
    public int hashCode() {
        int result = alpenists != null ? alpenists.hashCode() : 0;
        result = 31 * result + (mountain != null ? mountain.hashCode() : 0);
        result = 31 * result + (recruitmentStatus ? 1 : 0);
        result = 31 * result + maximumParticipants;
        result = 31 * result + (dateOfAscent != null ? dateOfAscent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "alpenists=" + alpenists +
                ", mountain=" + mountain +
                ", recruitmentStatus=" + recruitmentStatus +
                ", maximumParticipants=" + maximumParticipants +
                ", dateOfAscent=" + dateOfAscent +
                '}';
    }



    public void isOpenClose(){
        if (alpenists.size()>=maximumParticipants){
            recruitmentStatus = false;
        }
    }

    public void addAlpenist(Alpenist alpenist){
        isOpenClose();
        if (recruitmentStatus){
            alpenists.add(alpenist);
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormLesson");
            EntityManager manager = factory.createEntityManager();
            manager.getTransaction().begin();
            manager.merge(this);
            manager.getTransaction().commit();
        }
    }
}
