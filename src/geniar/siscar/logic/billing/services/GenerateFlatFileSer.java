package geniar.siscar.logic.billing.services;

import geniar.siscar.model.FlatFileVO;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

public interface GenerateFlatFileSer {

	public void generarArchivo(String carne, String concepto, String unidades,
			String valor, Date fecha, String documento, String centroCosto,
			String moneda, String descripcion) throws GWorkException;

	public void GeneradorArchivoPlano(List<FlatFileVO> listadoFlatFile,
			String Novedad) throws GWorkException;
}
