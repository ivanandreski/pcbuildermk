package mk.ukim.finki.pcbuildermkapi.domain.model

import jakarta.persistence.*

@Entity
data class Product(
    var name: String,
    var priceMkd: Double,
    var originalId: String,

    @Column(columnDefinition="TEXT")
    var originalUrl: String,

    @Column(columnDefinition="TEXT")
    var imageUrl: String?,

    @Column(columnDefinition="TEXT")
    var description: String,

    var manufacturer: String,

    @Column(columnDefinition = "boolean default false")
    var isDiscontinued: Boolean = false,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "compatibility_attributes_id", referencedColumnName = "id")
    var compatibilityAttributes: ProductCompatibilityAttributes? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category? = null,
) : BaseEntity() {
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    val productsInStoreLocation: List<ProductInStoreLocation> = ArrayList()

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    val priceHistory: List<ProductPriceHistory> = ArrayList()
}
