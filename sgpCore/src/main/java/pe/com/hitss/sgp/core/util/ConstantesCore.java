package pe.com.hitss.sgp.core.util;

public final class ConstantesCore {

	public static final String GIVEN_NAME="givenName";
	public static final String SN="sn";
	public static final String NAME="name";
	public static final String SAM_ACCOUNT_NAME="sAMAccountName";
	public static final String USER_PRINCIPAL_NAME="userPrincipalName";
	public static final String DISTINGUISHED_NAME="distinguishedName";
	
	public static final int N0 = 0;
	public static final int N1 = 1;
	public static final int N2 = 2;
	
	public static final String ESQUEMA_SGP = "HITSS";
	public static final String PACKAGE_SGP_SEGURIDAD = "PQ_SGP_SEGURIDAD";
	public static final String PACKAGE_SGP_ACTIVIDAD = "PQ_SGP_ACTIVIDAD";
	public static final String PACKAGE_SGP_MANTENIMIENTO = "PQ_SGP_MANTENIMIENTO_CFG";
	
	/* Para los comboBox AM: Account Manager, RE: Region, QA: Quarter, ST: Status
	 * jean Beuzeville - 12-01-2017--agregar constante QA1 para cargar todos QA para el read only. 
	 * Para los estados Active, Log*/
	public static final String LST_CMBX_AM = "AM";
	public static final String LST_CMBX_RE = "RE";
	public static final String LST_CMBX_QA = "QA";
	public static final String LST_CMBX_QA1 = "QA1";
	public static final String LST_CMBX_ST = "ST";
	public static final String ST_ACTIVE = "AC";
	public static final String ST_LOG = "LO";
	
	public static final String TAB1 = "TAB1";
	public static final String TAB2 = "TAB2";
	public static final String TAB3 = "TAB3";
	public static final String TAB4 = "TAB4";
	
	/* Para el tipo de tabla a actualiza*/
	public static final String T_STOCK_SERVICE = "STK";
	public static final String T_ARF_DETAIL = "ARD";
	
	public static final String PROCEDURE_PARAMETROS = "Procedimiento: {} Parámetros: {}";
	public static final String ERROR = "Ocurrió un error";
	
	public static final long CHECK_MARCADO = 1;
	public static final long CHECK_DESMARCADO = 0;
	
	public static final long FLG_ES_FILA_TOTAL_NO = 0;
	public static final long FLG_ES_FILA_TOTAL_SI = 1;
	
	//Style
	public static final String STYLE_ROJO = "text_rojo_bold";
	public static final String STYLE_CELESTE = "text_azul_bold";
	public static final String STYLE_NEGRITA = "text_negrita_bold";
	
	public static final String INITIATIVE = "Initiative";
	public static final String TOTAL = "TOTAL";

	
	private ConstantesCore() {

	}
}
