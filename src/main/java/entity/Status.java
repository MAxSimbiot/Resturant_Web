package entity;

public enum Status {

    PENDING("ОБРАБОТКА","PENDING","Заказ ожидает ответа менеджера","Pending manager response"),
    ACCEPTED("ПРИНЯТ","ACCEPTED","Заказ принят","Order accepted"),
    COOKING("ГОТОВИТСЯ","COOKING","Еда готовится!","We are cooking your food!"),
    ON_WAY("В ДОРОГЕ","ON WAY","На пути к вам!","Goods are on their way!"),
    PAID("ОПЛАЧЕН","PAID","Оплата прошла","Paid successfully"),
    DONE("ВЫПОЛНЕН","DONE","Готово.","All done."),
    CANCELLED("ОТМЕНЕН","CANCELLED","Отмена, увы..","Bad luck..");

    private String name_ru;
    private String name_us;
    private String description_ru;
    private String description_us;

    public  String getName_ru(){
        return this.name_ru;
    }
    public String getName_us(){
        return this.name_us;
    }
    public String getDescription_ru(){
        return this.description_ru;
    }
    public String getDescription_us(){
        return this.description_us;
    }

    private Status(String name_ru,String name_us,String description_ru,String description_us){
        this.name_ru = name_ru;
        this.name_us = name_us;
        this.description_ru = description_ru;
        this.description_us = description_us;
    }

    public static Status getByid(int id){
        return Status.values()[id-1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Status{");
        sb.append(", name_ru='").append(name_ru);
        sb.append(", name_us='").append(name_us);
        sb.append(", description_ru='").append(description_ru);
        sb.append(", description_us='").append(description_us).append("}");
        return sb.toString();
    }
}
