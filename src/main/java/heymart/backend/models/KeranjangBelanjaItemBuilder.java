package heymart.backend.models;

public class KeranjangBelanjaItemBuilder {
    private Long id;
    private KeranjangBelanja keranjangBelanja;
    private Product product;

    public KeranjangBelanjaItemBuilder setId(Long id){
        this.id = id;
        return this;
    }

    public KeranjangBelanjaItemBuilder setKeranjangBelanja(KeranjangBelanja keranjangBelanja){
        this.keranjangBelanja = keranjangBelanja;
        return this;
    }

    public KeranjangBelanjaItemBuilder setProduct(Product product) {
        this.product = product;
        return this;
    }

    public KeranjangBelanjaItem build() {
        return new KeranjangBelanjaItem(id, keranjangBelanja, product);
    }
}