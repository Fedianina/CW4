package tables;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import Help.ParentClass;

@Entity
@Table
public class Groups extends ParentClass {
    //Группа для восхождения на гору создаётся со следующими характеристиками и возможностями:
    //альпинисты
    //гора
    //идёт набор в группу или нет
    //максимальное количество участников
    //дата и время восхождения


    @Column(nullable = false)
    @OneToMany (mappedBy = "groups", orphanRemoval = true)
    private List <Alpenist> alpenists;

    @Column(nullable = false)
    @OneToOne(mappedBy = "groups")
    private Mountain mountain;

    @Column(nullable = false)
    private boolean recruitmentStatus;

    @Column(nullable = false)
    private int maximumParticipants = 0;

    @Column(nullable = false)
    private LocalDateTime dateOfAscent;


    public Groups() {
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


    public void isOpenClose(){
        if (alpenists.size()>=maximumParticipants){
            recruitmentStatus = false;
        }
    }

    public void addAlpenist(Alpenist alpenist){
        isOpenClose();
        if (recruitmentStatus){
            alpenists.add(alpenist);
           /* EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormLesson");
            EntityManager manager = factory.createEntityManager();
            manager.getTransaction().begin();// добавление класса в табл проходит в рамках транзакции
            manager.persist(alpenist);//хотим добавить объект, перечисляем все запроссы, которые дб выполнены
            manager.getTransaction().commit();
            alpenists.add(alpenist);
            repaint();*/
        }
    }
}
