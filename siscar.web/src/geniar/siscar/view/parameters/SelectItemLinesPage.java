package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.vehicle.services.LinesService;
import geniar.siscar.model.Lines;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.model.SelectItem;

public class SelectItemLinesPage {
	
	private SelectItem[] listLines;
	private List<Lines> listUtilLines;
	private LinesService linesService;
	
	public SelectItem[] getListLines() {
		if (getListUtilLines() != null && getListUtilLines().size() > 0) {
			listLines = new SelectItem[getListUtilLines().size() + 1];
			int i = 1;
			listLines[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (Lines lines : getListUtilLines()) {
				listLines[i] = new SelectItem(lines.getLnsId(), lines.getLnsNombre());
				i++;
			}
		}
		return listLines;
	}

	public void setListLines(SelectItem[] listLines) {
		this.listLines = listLines;
	}

	public List<Lines> getListUtilLines() {
		try {
			listUtilLines = SearchParameters.getAllLines();//linesService.listLineas();
		} catch (GWorkException e) {
			e.printStackTrace();
		}
		return listUtilLines;
	}

	public void setListUtilLines(List<Lines> listUtilLines) {
		this.listUtilLines = listUtilLines;
	}

	public LinesService getLinesService() {
		return linesService;
	}

	public void setLinesService(
			LinesService linesService) {
		this.linesService = linesService;
	}
}
