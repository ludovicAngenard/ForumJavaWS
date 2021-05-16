public class TopicDTO {
    private Integer id;
    private Boolean locked;

    public TopicDTO(){}

    public TopicDTO(Integer id, Boolean locked){
        this.id = id;
        this.locked = locked;
    }

    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return id;
    }
    public void setLocked(Boolean locked){
        this.locked = locked;
    }
    public Boolean getLocked(){
        return locked;
    }

}