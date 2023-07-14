package uk.co.vw.stock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockSearchRequest extends SearchRequest {

    private String[] paint;
    private String[] upholstery;
    private String[] status;
    private String scope;
    private String[] stockType;
    private String retailerNumber;
    private String[] orderType;
    private Integer size;
    private Integer start;
    private String[] sort;
    private String[] sortOrder;
    private String[] rch;
}
