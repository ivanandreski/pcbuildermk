package mk.ukim.finki.pcbuildermkapi.domain.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
class StoreLocation(
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    val store: Store,

    var address: String? = null,

    var email: String? = null,

    var phone: String? = null,

    var longitude: Long? = null,

    var latitude: Long? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @field:CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    lateinit var createdAt: LocalDateTime

    @field:UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    lateinit var modifiedAt: LocalDateTime

    @OneToMany(mappedBy = "storeLocation", cascade = [CascadeType.ALL], orphanRemoval = true)
    val products: List<ProductInStoreLocation> = ArrayList()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StoreLocation

        if (name != other.name) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }
}