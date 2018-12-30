package asc.msc.coursework.com.expensetracker.dto;

/**
 * 
 */
public class Category {

    /**
     * Default constructor
     */
    public Category() {
    }

    /**
     * 
     */
    private int categoryID;

    /**
     * 
     */
    private String categoryName;

    /**
     * 
     */
    private Double estimatedAmount;


    /**
     *
     * @param categoryID
     * @param name
     * @param amount
     * @return
     */
    public boolean addCategory(int categoryID, String name, Double amount) {

        // TODO implement here
        return false ;
    }

    /**
     *
     * @param category
     * @return
     */
    public Boolean updateCategory(Category category) {
        // TODO implement here
        return null;
    }

    /**
     *
     * @param deletedcat
     * @return
     */
    public Boolean deleteCategory(Category deletedcat) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Category viewCategory() {
        // TODO implement here
        return null;
    }

}