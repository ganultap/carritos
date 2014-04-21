package geniar.siscar.model;


public class VOCostCentersFuels {

	private Long ccfId;
	private Long idPrepaid;
	private Long idCostsCenters;
	private Long idCostCenterTypeFuel;
	private String costCenterNumber;
	

	public VOCostCentersFuels() {		
	}
	public Long getCcfId() {
		return ccfId;
	}
	public void setCcfId(Long ccfId) {
		this.ccfId = ccfId;
	}
	public Long getIdPrepaid() {
		return idPrepaid;
	}
	public void setIdPrepaid(Long idPrepaid) {
		this.idPrepaid = idPrepaid;
	}
	public Long getIdCostsCenters() {
		return idCostsCenters;
	}
	public void setIdCostsCenters(Long idCostsCenters) {
		this.idCostsCenters = idCostsCenters;
	}
	public Long getIdCostCenterTypeFuel() {
		return idCostCenterTypeFuel;
	}
	public void setIdCostCenterTypeFuel(Long idCostCenterTypeFuel) {
		this.idCostCenterTypeFuel = idCostCenterTypeFuel;
	}
	public String getCostCenterNumber() {
		return costCenterNumber;
	}
	public void setCostCenterNumber(String costCenterNumber) {
		this.costCenterNumber = costCenterNumber;
	}	

}
