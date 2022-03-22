package foundation;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

public class Worker {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Float salary; //Поле может быть null, Значение поля должно быть больше 0
    private final Date startDate; //Поле не может быть null
    private final Position position; //Поле может быть null
    private final Status status; //Поле может быть null
    private final Organization organization; //Поле не может быть null

    public Worker(int id,String name, Coordinates coordinates, ZonedDateTime creationDate, Float salary, Date startDate, Position position,Status status,Organization organization){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.salary = salary;
        this.startDate = startDate;
        this.position = position;
        this.status = status;
        this.organization = organization;
    }

    public String getName() {return name;}
    public int getId(){return id;}
    public Coordinates getCoordinates(){return coordinates;}
    public ZonedDateTime getCreationDate(){return creationDate;}
    public Float getSalary(){return salary;}
    public Date getStartDate() {return startDate;}
    public Position getPosition(){return position;}
    public Status getStatus(){return status;}
    public Organization getOrganization(){return organization;}

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return id == worker.id && Objects.equals(name, worker.name) && Objects.equals(coordinates, worker.coordinates) && Objects.equals(creationDate, worker.creationDate) && Objects.equals(salary, worker.salary) && Objects.equals(startDate, worker.startDate) && position == worker.position && status == worker.status && Objects.equals(organization, worker.organization);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return  "[id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", startDate=" + startDate +
                ", position=" + position +
                ", status=" + status +
                ", organization=" + organization +
                ']';
    }

    public int compareTo(Date date) {
        return (startDate.before(date) ? -1 : (startDate==date ? 0 : 1));
    }
}
