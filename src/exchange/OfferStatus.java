package exchange;

/**
 * Enum name: OfferStatus
 * <p>
 * Description: It defines the offer's possible statuses
 * @author Ana O.R.
 * @version 1.0
 */
public enum OfferStatus {
    /* The offer was made but is yet to be accepted or rejected */
    PENDING,
    /* The offer was cancelled by the sender */
    CANCELED,
    /* The offer expired */
    EXPIRED,
    /* The offer was rejected */
    REJECTED,
    /* The offer was rejected */
    ACCEPTED,
    /* The offer was accepted and processed */
    FINALISED
}