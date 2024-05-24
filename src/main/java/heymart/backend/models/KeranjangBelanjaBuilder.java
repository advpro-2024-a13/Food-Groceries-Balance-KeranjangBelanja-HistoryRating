package heymart.backend.models;

public class KeranjangBelanjaBuilder {
    private Long id;
    private Long ownerId;
    public KeranjangBelanjaBuilder setKeranjangBelanjaId(Long id){
        this.id = id;
        return this;
    }

    public KeranjangBelanjaBuilder setOwnerId(Long ownerId){
        this.ownerId = ownerId;
        return this;
    }

    public KeranjangBelanja build(){
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setId(this.id);
        keranjangBelanja.setOwnerId(this.ownerId);
        return keranjangBelanja;
    }
}