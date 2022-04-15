// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package local_project.vendorsv3td_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: VendorsV3TD Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class VendorsV3TD implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "VendorsV3TD";
	private final String projectName = "LOCAL_PROJECT";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					VendorsV3TD.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(VendorsV3TD.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tRESTClient_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tExtractJSONFields_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tRESTClient_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tRESTClient_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_VendorsV3TD = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[0];

		public String VendorGroupId;

		public String getVendorGroupId() {
			return this.VendorGroupId;
		}

		public String CUSIPIdentificationNumber;

		public String getCUSIPIdentificationNumber() {
			return this.CUSIPIdentificationNumber;
		}

		public String VendorAccountNumber;

		public String getVendorAccountNumber() {
			return this.VendorAccountNumber;
		}

		public String VendorOrganizationName;

		public String getVendorOrganizationName() {
			return this.VendorOrganizationName;
		}

		public String CurrencyCode;

		public String getCurrencyCode() {
			return this.CurrencyCode;
		}

		public String AddressDescription;

		public String getAddressDescription() {
			return this.AddressDescription;
		}

		public String AddressStreet;

		public String getAddressStreet() {
			return this.AddressStreet;
		}

		public String AddressZipCode;

		public String getAddressZipCode() {
			return this.AddressZipCode;
		}

		public String AddressCity;

		public String getAddressCity() {
			return this.AddressCity;
		}

		public String LanguageId;

		public String getLanguageId() {
			return this.LanguageId;
		}

		public String NAFCode;

		public String getNAFCode() {
			return this.NAFCode;
		}

		public String AddressCountryRegionId;

		public String getAddressCountryRegionId() {
			return this.AddressCountryRegionId;
		}

		public String InvoiceVendorAccountNumber;

		public String getInvoiceVendorAccountNumber() {
			return this.InvoiceVendorAccountNumber;
		}

		public String DefaultPaymentTermsName;

		public String getDefaultPaymentTermsName() {
			return this.DefaultPaymentTermsName;
		}

		public String DefaultVendorPaymentMethodName;

		public String getDefaultVendorPaymentMethodName() {
			return this.DefaultVendorPaymentMethodName;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_VendorsV3TD.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_VendorsV3TD.length == 0) {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_VendorsV3TD.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_VendorsV3TD.length == 0) {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_VendorsV3TD) {

				try {

					int length = 0;

					this.VendorGroupId = readString(dis);

					this.CUSIPIdentificationNumber = readString(dis);

					this.VendorAccountNumber = readString(dis);

					this.VendorOrganizationName = readString(dis);

					this.CurrencyCode = readString(dis);

					this.AddressDescription = readString(dis);

					this.AddressStreet = readString(dis);

					this.AddressZipCode = readString(dis);

					this.AddressCity = readString(dis);

					this.LanguageId = readString(dis);

					this.NAFCode = readString(dis);

					this.AddressCountryRegionId = readString(dis);

					this.InvoiceVendorAccountNumber = readString(dis);

					this.DefaultPaymentTermsName = readString(dis);

					this.DefaultVendorPaymentMethodName = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_VendorsV3TD) {

				try {

					int length = 0;

					this.VendorGroupId = readString(dis);

					this.CUSIPIdentificationNumber = readString(dis);

					this.VendorAccountNumber = readString(dis);

					this.VendorOrganizationName = readString(dis);

					this.CurrencyCode = readString(dis);

					this.AddressDescription = readString(dis);

					this.AddressStreet = readString(dis);

					this.AddressZipCode = readString(dis);

					this.AddressCity = readString(dis);

					this.LanguageId = readString(dis);

					this.NAFCode = readString(dis);

					this.AddressCountryRegionId = readString(dis);

					this.InvoiceVendorAccountNumber = readString(dis);

					this.DefaultPaymentTermsName = readString(dis);

					this.DefaultVendorPaymentMethodName = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.VendorGroupId, dos);

				// String

				writeString(this.CUSIPIdentificationNumber, dos);

				// String

				writeString(this.VendorAccountNumber, dos);

				// String

				writeString(this.VendorOrganizationName, dos);

				// String

				writeString(this.CurrencyCode, dos);

				// String

				writeString(this.AddressDescription, dos);

				// String

				writeString(this.AddressStreet, dos);

				// String

				writeString(this.AddressZipCode, dos);

				// String

				writeString(this.AddressCity, dos);

				// String

				writeString(this.LanguageId, dos);

				// String

				writeString(this.NAFCode, dos);

				// String

				writeString(this.AddressCountryRegionId, dos);

				// String

				writeString(this.InvoiceVendorAccountNumber, dos);

				// String

				writeString(this.DefaultPaymentTermsName, dos);

				// String

				writeString(this.DefaultVendorPaymentMethodName, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.VendorGroupId, dos);

				// String

				writeString(this.CUSIPIdentificationNumber, dos);

				// String

				writeString(this.VendorAccountNumber, dos);

				// String

				writeString(this.VendorOrganizationName, dos);

				// String

				writeString(this.CurrencyCode, dos);

				// String

				writeString(this.AddressDescription, dos);

				// String

				writeString(this.AddressStreet, dos);

				// String

				writeString(this.AddressZipCode, dos);

				// String

				writeString(this.AddressCity, dos);

				// String

				writeString(this.LanguageId, dos);

				// String

				writeString(this.NAFCode, dos);

				// String

				writeString(this.AddressCountryRegionId, dos);

				// String

				writeString(this.InvoiceVendorAccountNumber, dos);

				// String

				writeString(this.DefaultPaymentTermsName, dos);

				// String

				writeString(this.DefaultVendorPaymentMethodName, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("VendorGroupId=" + VendorGroupId);
			sb.append(",CUSIPIdentificationNumber=" + CUSIPIdentificationNumber);
			sb.append(",VendorAccountNumber=" + VendorAccountNumber);
			sb.append(",VendorOrganizationName=" + VendorOrganizationName);
			sb.append(",CurrencyCode=" + CurrencyCode);
			sb.append(",AddressDescription=" + AddressDescription);
			sb.append(",AddressStreet=" + AddressStreet);
			sb.append(",AddressZipCode=" + AddressZipCode);
			sb.append(",AddressCity=" + AddressCity);
			sb.append(",LanguageId=" + LanguageId);
			sb.append(",NAFCode=" + NAFCode);
			sb.append(",AddressCountryRegionId=" + AddressCountryRegionId);
			sb.append(",InvoiceVendorAccountNumber=" + InvoiceVendorAccountNumber);
			sb.append(",DefaultPaymentTermsName=" + DefaultPaymentTermsName);
			sb.append(",DefaultVendorPaymentMethodName=" + DefaultVendorPaymentMethodName);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_VendorsV3TD = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[0];

		public String VendorGroupId;

		public String getVendorGroupId() {
			return this.VendorGroupId;
		}

		public String CUSIPIdentificationNumber;

		public String getCUSIPIdentificationNumber() {
			return this.CUSIPIdentificationNumber;
		}

		public String VendorAccountNumber;

		public String getVendorAccountNumber() {
			return this.VendorAccountNumber;
		}

		public String VendorOrganizationName;

		public String getVendorOrganizationName() {
			return this.VendorOrganizationName;
		}

		public String CurrencyCode;

		public String getCurrencyCode() {
			return this.CurrencyCode;
		}

		public String AddressDescription;

		public String getAddressDescription() {
			return this.AddressDescription;
		}

		public String AddressStreet;

		public String getAddressStreet() {
			return this.AddressStreet;
		}

		public String AddressZipCode;

		public String getAddressZipCode() {
			return this.AddressZipCode;
		}

		public String AddressCity;

		public String getAddressCity() {
			return this.AddressCity;
		}

		public String LanguageId;

		public String getLanguageId() {
			return this.LanguageId;
		}

		public String NAFCode;

		public String getNAFCode() {
			return this.NAFCode;
		}

		public String AddressCountryRegionId;

		public String getAddressCountryRegionId() {
			return this.AddressCountryRegionId;
		}

		public String InvoiceVendorAccountNumber;

		public String getInvoiceVendorAccountNumber() {
			return this.InvoiceVendorAccountNumber;
		}

		public String DefaultPaymentTermsName;

		public String getDefaultPaymentTermsName() {
			return this.DefaultPaymentTermsName;
		}

		public String DefaultVendorPaymentMethodName;

		public String getDefaultVendorPaymentMethodName() {
			return this.DefaultVendorPaymentMethodName;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_VendorsV3TD.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_VendorsV3TD.length == 0) {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_VendorsV3TD.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_VendorsV3TD.length == 0) {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_VendorsV3TD) {

				try {

					int length = 0;

					this.VendorGroupId = readString(dis);

					this.CUSIPIdentificationNumber = readString(dis);

					this.VendorAccountNumber = readString(dis);

					this.VendorOrganizationName = readString(dis);

					this.CurrencyCode = readString(dis);

					this.AddressDescription = readString(dis);

					this.AddressStreet = readString(dis);

					this.AddressZipCode = readString(dis);

					this.AddressCity = readString(dis);

					this.LanguageId = readString(dis);

					this.NAFCode = readString(dis);

					this.AddressCountryRegionId = readString(dis);

					this.InvoiceVendorAccountNumber = readString(dis);

					this.DefaultPaymentTermsName = readString(dis);

					this.DefaultVendorPaymentMethodName = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_VendorsV3TD) {

				try {

					int length = 0;

					this.VendorGroupId = readString(dis);

					this.CUSIPIdentificationNumber = readString(dis);

					this.VendorAccountNumber = readString(dis);

					this.VendorOrganizationName = readString(dis);

					this.CurrencyCode = readString(dis);

					this.AddressDescription = readString(dis);

					this.AddressStreet = readString(dis);

					this.AddressZipCode = readString(dis);

					this.AddressCity = readString(dis);

					this.LanguageId = readString(dis);

					this.NAFCode = readString(dis);

					this.AddressCountryRegionId = readString(dis);

					this.InvoiceVendorAccountNumber = readString(dis);

					this.DefaultPaymentTermsName = readString(dis);

					this.DefaultVendorPaymentMethodName = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.VendorGroupId, dos);

				// String

				writeString(this.CUSIPIdentificationNumber, dos);

				// String

				writeString(this.VendorAccountNumber, dos);

				// String

				writeString(this.VendorOrganizationName, dos);

				// String

				writeString(this.CurrencyCode, dos);

				// String

				writeString(this.AddressDescription, dos);

				// String

				writeString(this.AddressStreet, dos);

				// String

				writeString(this.AddressZipCode, dos);

				// String

				writeString(this.AddressCity, dos);

				// String

				writeString(this.LanguageId, dos);

				// String

				writeString(this.NAFCode, dos);

				// String

				writeString(this.AddressCountryRegionId, dos);

				// String

				writeString(this.InvoiceVendorAccountNumber, dos);

				// String

				writeString(this.DefaultPaymentTermsName, dos);

				// String

				writeString(this.DefaultVendorPaymentMethodName, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.VendorGroupId, dos);

				// String

				writeString(this.CUSIPIdentificationNumber, dos);

				// String

				writeString(this.VendorAccountNumber, dos);

				// String

				writeString(this.VendorOrganizationName, dos);

				// String

				writeString(this.CurrencyCode, dos);

				// String

				writeString(this.AddressDescription, dos);

				// String

				writeString(this.AddressStreet, dos);

				// String

				writeString(this.AddressZipCode, dos);

				// String

				writeString(this.AddressCity, dos);

				// String

				writeString(this.LanguageId, dos);

				// String

				writeString(this.NAFCode, dos);

				// String

				writeString(this.AddressCountryRegionId, dos);

				// String

				writeString(this.InvoiceVendorAccountNumber, dos);

				// String

				writeString(this.DefaultPaymentTermsName, dos);

				// String

				writeString(this.DefaultVendorPaymentMethodName, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("VendorGroupId=" + VendorGroupId);
			sb.append(",CUSIPIdentificationNumber=" + CUSIPIdentificationNumber);
			sb.append(",VendorAccountNumber=" + VendorAccountNumber);
			sb.append(",VendorOrganizationName=" + VendorOrganizationName);
			sb.append(",CurrencyCode=" + CurrencyCode);
			sb.append(",AddressDescription=" + AddressDescription);
			sb.append(",AddressStreet=" + AddressStreet);
			sb.append(",AddressZipCode=" + AddressZipCode);
			sb.append(",AddressCity=" + AddressCity);
			sb.append(",LanguageId=" + LanguageId);
			sb.append(",NAFCode=" + NAFCode);
			sb.append(",AddressCountryRegionId=" + AddressCountryRegionId);
			sb.append(",InvoiceVendorAccountNumber=" + InvoiceVendorAccountNumber);
			sb.append(",DefaultPaymentTermsName=" + DefaultPaymentTermsName);
			sb.append(",DefaultVendorPaymentMethodName=" + DefaultVendorPaymentMethodName);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_LOCAL_PROJECT_VendorsV3TD = new byte[0];
		static byte[] commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[0];

		public Integer statusCode;

		public Integer getStatusCode() {
			return this.statusCode;
		}

		public routines.system.Document body;

		public routines.system.Document getBody() {
			return this.body;
		}

		public String string;

		public String getString() {
			return this.string;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_VendorsV3TD.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_VendorsV3TD.length == 0) {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_LOCAL_PROJECT_VendorsV3TD.length) {
					if (length < 1024 && commonByteArray_LOCAL_PROJECT_VendorsV3TD.length == 0) {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[1024];
					} else {
						commonByteArray_LOCAL_PROJECT_VendorsV3TD = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length);
				strReturn = new String(commonByteArray_LOCAL_PROJECT_VendorsV3TD, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_VendorsV3TD) {

				try {

					int length = 0;

					this.statusCode = readInteger(dis);

					this.body = (routines.system.Document) dis.readObject();

					this.string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_LOCAL_PROJECT_VendorsV3TD) {

				try {

					int length = 0;

					this.statusCode = readInteger(dis);

					this.body = (routines.system.Document) dis.readObject();

					this.string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.statusCode, dos);

				// Document

				dos.writeObject(this.body);

				// String

				writeString(this.string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.statusCode, dos);

				// Document

				dos.writeObject(this.body);

				// String

				writeString(this.string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("statusCode=" + String.valueOf(statusCode));
			sb.append(",body=" + String.valueOf(body));
			sb.append(",string=" + string);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tRESTClient_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tRESTClient_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				row2Struct row2 = new row2Struct();
				row2Struct row4 = row2;

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tDBOutput_1 = 0;

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;
				String dbschema_tDBOutput_1 = null;
				String tableName_tDBOutput_1 = null;
				boolean whetherReject_tDBOutput_1 = false;

				java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
				long year1_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_1;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_1 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_1 = null;
				String dbUser_tDBOutput_1 = null;
				dbschema_tDBOutput_1 = "";
				String driverClass_tDBOutput_1 = "net.sourceforge.jtds.jdbc.Driver";

				java.lang.Class.forName(driverClass_tDBOutput_1);
				String port_tDBOutput_1 = "1433";
				String dbname_tDBOutput_1 = "base";
				String url_tDBOutput_1 = "jdbc:jtds:sqlserver://" + "localhost";
				if (!"".equals(port_tDBOutput_1)) {
					url_tDBOutput_1 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_1)) {
					url_tDBOutput_1 += "//" + "base";

				}
				url_tDBOutput_1 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_1 = "nadia";

				final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:dDJvB4NrREpO0zksZQ8q1BFlprgb6LWcM3hJAVaBKLqhkQ==");

				String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
				conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
						dbPwd_tDBOutput_1);

				resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);

				conn_tDBOutput_1.setAutoCommit(false);
				int commitEvery_tDBOutput_1 = 10000;
				int commitCounter_tDBOutput_1 = 0;

				int batchSize_tDBOutput_1 = 10000;
				int batchSizeCounter_tDBOutput_1 = 0;

				if (dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
					tableName_tDBOutput_1 = "VendorsV3TD";
				} else {
					tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "].[" + "VendorsV3TD";
				}
				int count_tDBOutput_1 = 0;

				try (java.sql.Statement stmtCreate_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
					stmtCreate_tDBOutput_1.execute("CREATE TABLE [" + tableName_tDBOutput_1
							+ "]([VendorGroupId] VARCHAR(50)  ,[CUSIPIdentificationNumber] VARCHAR(50)  ,[VendorAccountNumber] VARCHAR(50)  ,[VendorOrganizationName] VARCHAR(50)  ,[CurrencyCode] VARCHAR(50)  ,[AddressDescription] VARCHAR(50)  ,[AddressStreet] VARCHAR(50)  ,[AddressZipCode] VARCHAR(50)  ,[AddressCity] VARCHAR(50)  ,[LanguageId] VARCHAR(50)  ,[NAFCode] VARCHAR(50)  ,[AddressCountryRegionId] VARCHAR(50)  ,[InvoiceVendorAccountNumber] VARCHAR(50)  ,[DefaultPaymentTermsName] VARCHAR(50)  ,[DefaultVendorPaymentMethodName] VARCHAR(50)  )");
				}
				String insert_tDBOutput_1 = "INSERT INTO [" + tableName_tDBOutput_1
						+ "] ([VendorGroupId],[CUSIPIdentificationNumber],[VendorAccountNumber],[VendorOrganizationName],[CurrencyCode],[AddressDescription],[AddressStreet],[AddressZipCode],[AddressCity],[LanguageId],[NAFCode],[AddressCountryRegionId],[InvoiceVendorAccountNumber],[DefaultPaymentTermsName],[DefaultVendorPaymentMethodName]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[15];

					public void addRow(String[] row) {

						for (int i = 0; i < 15; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 14 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 14 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|%9$-");
							sbformat.append(colLengths[8]);
							sbformat.append("s");

							sbformat.append("|%10$-");
							sbformat.append(colLengths[9]);
							sbformat.append("s");

							sbformat.append("|%11$-");
							sbformat.append(colLengths[10]);
							sbformat.append("s");

							sbformat.append("|%12$-");
							sbformat.append(colLengths[11]);
							sbformat.append("s");

							sbformat.append("|%13$-");
							sbformat.append(colLengths[12]);
							sbformat.append("s");

							sbformat.append("|%14$-");
							sbformat.append(colLengths[13]);
							sbformat.append("s");

							sbformat.append("|%15$-");
							sbformat.append(colLengths[14]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[7] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[8] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[9] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[10] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[11] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[12] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[13] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[14] - fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "VendorGroupId", "CUSIPIdentificationNumber",
						"VendorAccountNumber", "VendorOrganizationName", "CurrencyCode", "AddressDescription",
						"AddressStreet", "AddressZipCode", "AddressCity", "LanguageId", "NAFCode",
						"AddressCountryRegionId", "InvoiceVendorAccountNumber", "DefaultPaymentTermsName",
						"DefaultVendorPaymentMethodName", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tExtractJSONFields_1 begin ] start
				 */

				ok_Hash.put("tExtractJSONFields_1", false);
				start_Hash.put("tExtractJSONFields_1", System.currentTimeMillis());

				currentComponent = "tExtractJSONFields_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tExtractJSONFields_1 = 0;

				int nb_line_tExtractJSONFields_1 = 0;
				String jsonStr_tExtractJSONFields_1 = "";

				class JsonPathCache_tExtractJSONFields_1 {
					final java.util.Map<String, com.jayway.jsonpath.JsonPath> jsonPathString2compiledJsonPath = new java.util.HashMap<String, com.jayway.jsonpath.JsonPath>();

					public com.jayway.jsonpath.JsonPath getCompiledJsonPath(String jsonPath) {
						if (jsonPathString2compiledJsonPath.containsKey(jsonPath)) {
							return jsonPathString2compiledJsonPath.get(jsonPath);
						} else {
							com.jayway.jsonpath.JsonPath compiledLoopPath = com.jayway.jsonpath.JsonPath
									.compile(jsonPath);
							jsonPathString2compiledJsonPath.put(jsonPath, compiledLoopPath);
							return compiledLoopPath;
						}
					}
				}

				JsonPathCache_tExtractJSONFields_1 jsonPathCache_tExtractJSONFields_1 = new JsonPathCache_tExtractJSONFields_1();

				/**
				 * [tExtractJSONFields_1 begin ] stop
				 */

				/**
				 * [tRESTClient_1 begin ] start
				 */

				ok_Hash.put("tRESTClient_1", false);
				start_Hash.put("tRESTClient_1", System.currentTimeMillis());

				currentComponent = "tRESTClient_1";

				int tos_count_tRESTClient_1 = 0;

				/**
				 * [tRESTClient_1 begin ] stop
				 */

				/**
				 * [tRESTClient_1 main ] start
				 */

				currentComponent = "tRESTClient_1";

				row1 = null;

// expected response body
				Object responseDoc_tRESTClient_1 = null;

				try {
					// request body
					org.dom4j.Document requestDoc_tRESTClient_1 = null;
					String requestString_tRESTClient_1 = null;

					Object requestBody_tRESTClient_1 = requestDoc_tRESTClient_1 != null ? requestDoc_tRESTClient_1
							: requestString_tRESTClient_1;

					// resposne class name
					Class<?> responseClass_tRESTClient_1 = String.class;

					// create web client instance
					org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean factoryBean_tRESTClient_1 = new org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean();

					boolean inOSGi = routines.system.BundleUtils.inOSGi();

					final java.util.List<org.apache.cxf.feature.Feature> features_tRESTClient_1 = new java.util.ArrayList<org.apache.cxf.feature.Feature>();

					String url = "http://localhost:3000";
					// {baseUri}tRESTClient
					factoryBean_tRESTClient_1.setServiceName(new javax.xml.namespace.QName(url, "tRESTClient"));
					factoryBean_tRESTClient_1.setAddress(url);

					factoryBean_tRESTClient_1.setFeatures(features_tRESTClient_1);

					java.util.List<Object> providers_tRESTClient_1 = new java.util.ArrayList<Object>();
					providers_tRESTClient_1.add(new org.apache.cxf.jaxrs.provider.dom4j.DOM4JProvider() {
						// workaround for https://jira.talendforge.org/browse/TESB-7276
						public org.dom4j.Document readFrom(Class<org.dom4j.Document> cls, java.lang.reflect.Type type,
								java.lang.annotation.Annotation[] anns, javax.ws.rs.core.MediaType mt,
								javax.ws.rs.core.MultivaluedMap<String, String> headers, java.io.InputStream is)
								throws IOException, javax.ws.rs.WebApplicationException {
							String contentLength = headers.getFirst("Content-Length");
							if (!org.apache.cxf.common.util.StringUtils.isEmpty(contentLength)
									&& Integer.valueOf(contentLength) <= 0) {
								try {
									return org.dom4j.DocumentHelper.parseText("<root/>");
								} catch (org.dom4j.DocumentException e_tRESTClient_1) {
									e_tRESTClient_1.printStackTrace();
								}
								return null;
							}
							return super.readFrom(cls, type, anns, mt, headers, is);
						}
					});
					org.apache.cxf.jaxrs.provider.json.JSONProvider jsonProvider_tRESTClient_1 = new org.apache.cxf.jaxrs.provider.json.JSONProvider();
					jsonProvider_tRESTClient_1.setIgnoreNamespaces(true);
					jsonProvider_tRESTClient_1.setAttributesToElements(true);

					jsonProvider_tRESTClient_1.setSupportUnwrapped(true);
					jsonProvider_tRESTClient_1.setWrapperName("root");

					jsonProvider_tRESTClient_1.setDropRootElement(false);
					jsonProvider_tRESTClient_1.setConvertTypesToStrings(false);
					providers_tRESTClient_1.add(jsonProvider_tRESTClient_1);
					factoryBean_tRESTClient_1.setProviders(providers_tRESTClient_1);
					factoryBean_tRESTClient_1.setTransportId("http://cxf.apache.org/transports/http");

					boolean use_auth_tRESTClient_1 = false;

					org.apache.cxf.jaxrs.client.WebClient webClient_tRESTClient_1 = factoryBean_tRESTClient_1
							.createWebClient();

					// set request path
					webClient_tRESTClient_1.path("/VendorsV3TD");

					// set connection properties
					org.apache.cxf.jaxrs.client.ClientConfiguration clientConfig_tRESTClient_1 = org.apache.cxf.jaxrs.client.WebClient
							.getConfig(webClient_tRESTClient_1);
					org.apache.cxf.transport.http.auth.HttpAuthSupplier httpAuthSupplerHttpConduit = null;
					org.apache.cxf.transport.http.HTTPConduit conduit_tRESTClient_1 = clientConfig_tRESTClient_1
							.getHttpConduit();

					if (clientConfig_tRESTClient_1.getEndpoint() != null) {
						org.apache.cxf.service.model.EndpointInfo endpointInfo_tRESTClient_1 = clientConfig_tRESTClient_1
								.getEndpoint().getEndpointInfo();
						if (endpointInfo_tRESTClient_1 != null) {
							endpointInfo_tRESTClient_1.setProperty("enable.webclient.operation.reporting", true);
						}
					}

					if (!inOSGi) {
						conduit_tRESTClient_1.getClient().setReceiveTimeout((long) (60 * 1000L));
						conduit_tRESTClient_1.getClient().setConnectionTimeout((long) (30 * 1000L));
						boolean use_proxy_tRESTClient_1 = false;

					}

					// set Accept-Type
					webClient_tRESTClient_1.accept("*/*");

					// set optional query and header properties if any

					if (use_auth_tRESTClient_1 && "OAUTH2_BEARER".equals("BASIC")) {
						// set oAuth2 bearer token
						org.apache.cxf.rs.security.oauth2.client.BearerAuthSupplier authSupplier = new org.apache.cxf.rs.security.oauth2.client.BearerAuthSupplier();
						authSupplier.setAccessToken("");
						conduit_tRESTClient_1.setAuthSupplier(authSupplier);
					}

					// if FORM request then capture query parameters into Form, otherwise set them
					// as queries

					try {
						// start send request

						responseDoc_tRESTClient_1 = webClient_tRESTClient_1.get();
						javax.ws.rs.core.Response responseObjBase_tRESTClient_1 = (javax.ws.rs.core.Response) responseDoc_tRESTClient_1;
						int status_tRESTClient_1 = responseObjBase_tRESTClient_1.getStatus();
						if (status_tRESTClient_1 != 304 && status_tRESTClient_1 >= 300
								&& responseClass_tRESTClient_1 != javax.ws.rs.core.Response.class) {
							throw org.apache.cxf.jaxrs.utils.ExceptionUtils.toWebApplicationException(
									(javax.ws.rs.core.Response) responseObjBase_tRESTClient_1);
						}
						if (responseObjBase_tRESTClient_1.getEntity() != null) {
							responseDoc_tRESTClient_1 = responseObjBase_tRESTClient_1
									.readEntity(responseClass_tRESTClient_1);
						}

						int webClientResponseStatus_tRESTClient_1 = webClient_tRESTClient_1.getResponse().getStatus();
						if (webClientResponseStatus_tRESTClient_1 >= 300) {
							throw new javax.ws.rs.WebApplicationException(webClient_tRESTClient_1.getResponse());
						}

						if (row1 == null) {
							row1 = new row1Struct();
						}

						row1.statusCode = webClientResponseStatus_tRESTClient_1;
						row1.string = "";

						{
							Object responseObj_tRESTClient_1 = responseDoc_tRESTClient_1;

							if (responseObj_tRESTClient_1 != null) {
								if (responseClass_tRESTClient_1 == String.class
										&& responseObj_tRESTClient_1 instanceof String) {
									row1.string = (String) responseObj_tRESTClient_1;
								} else {
									routines.system.Document responseTalendDoc_tRESTClient_1 = null;
									if (null != responseObj_tRESTClient_1) {
										responseTalendDoc_tRESTClient_1 = new routines.system.Document();
										if (responseObj_tRESTClient_1 instanceof org.dom4j.Document) {
											responseTalendDoc_tRESTClient_1
													.setDocument((org.dom4j.Document) responseObj_tRESTClient_1);
										}
									}
									row1.body = responseTalendDoc_tRESTClient_1;
								}
							}
						}

						globalMap.put("tRESTClient_1_HEADERS", webClient_tRESTClient_1.getResponse().getHeaders());
						globalMap.put("tRESTClient_1_COOKIES", webClient_tRESTClient_1.getResponse().getCookies());

					} catch (javax.ws.rs.WebApplicationException ex_tRESTClient_1) {
						globalMap.put("tRESTClient_1_ERROR_MESSAGE", ex_tRESTClient_1.getMessage());

						throw ex_tRESTClient_1;

					}

				} catch (Exception e_tRESTClient_1) {
					globalMap.put("tRESTClient_1_ERROR_MESSAGE", e_tRESTClient_1.getMessage());

					throw new TalendException(e_tRESTClient_1, currentComponent, globalMap);

				}

				tos_count_tRESTClient_1++;

				/**
				 * [tRESTClient_1 main ] stop
				 */

				/**
				 * [tRESTClient_1 process_data_begin ] start
				 */

				currentComponent = "tRESTClient_1";

				/**
				 * [tRESTClient_1 process_data_begin ] stop
				 */
// Start of branch "row1"
				if (row1 != null) {

					/**
					 * [tExtractJSONFields_1 main ] start
					 */

					currentComponent = "tExtractJSONFields_1";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row1"

						);
					}

					if (row1.string != null) {// C_01
						jsonStr_tExtractJSONFields_1 = row1.string.toString();

						row2 = null;

						String loopPath_tExtractJSONFields_1 = "$.VendorsV3TD";
						java.util.List<Object> resultset_tExtractJSONFields_1 = new java.util.ArrayList<Object>();

						boolean isStructError_tExtractJSONFields_1 = true;
						com.jayway.jsonpath.ReadContext document_tExtractJSONFields_1 = null;
						try {
							document_tExtractJSONFields_1 = com.jayway.jsonpath.JsonPath
									.parse(jsonStr_tExtractJSONFields_1);
							com.jayway.jsonpath.JsonPath compiledLoopPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
									.getCompiledJsonPath(loopPath_tExtractJSONFields_1);
							Object result_tExtractJSONFields_1 = document_tExtractJSONFields_1
									.read(compiledLoopPath_tExtractJSONFields_1, net.minidev.json.JSONObject.class);
							if (result_tExtractJSONFields_1 instanceof net.minidev.json.JSONArray) {
								resultset_tExtractJSONFields_1 = (net.minidev.json.JSONArray) result_tExtractJSONFields_1;
							} else {
								resultset_tExtractJSONFields_1.add(result_tExtractJSONFields_1);
							}

							isStructError_tExtractJSONFields_1 = false;
						} catch (java.lang.Exception ex_tExtractJSONFields_1) {
							globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE", ex_tExtractJSONFields_1.getMessage());
							System.err.println(ex_tExtractJSONFields_1.getMessage());
						}

						String jsonPath_tExtractJSONFields_1 = null;
						com.jayway.jsonpath.JsonPath compiledJsonPath_tExtractJSONFields_1 = null;

						Object value_tExtractJSONFields_1 = null;

						Object root_tExtractJSONFields_1 = null;
						for (int i_tExtractJSONFields_1 = 0; isStructError_tExtractJSONFields_1
								|| (i_tExtractJSONFields_1 < resultset_tExtractJSONFields_1
										.size()); i_tExtractJSONFields_1++) {
							if (!isStructError_tExtractJSONFields_1) {
								Object row_tExtractJSONFields_1 = resultset_tExtractJSONFields_1
										.get(i_tExtractJSONFields_1);
								row2 = null;
								row2 = new row2Struct();
								nb_line_tExtractJSONFields_1++;
								try {
									jsonPath_tExtractJSONFields_1 = "VendorGroupId";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.VendorGroupId = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.VendorGroupId =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "CUSIPIdentificationNumber";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.CUSIPIdentificationNumber = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.CUSIPIdentificationNumber =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "VendorAccountNumber";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.VendorAccountNumber = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.VendorAccountNumber =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "VendorOrganizationName";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.VendorOrganizationName = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.VendorOrganizationName =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "CurrencyCode";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.CurrencyCode = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.CurrencyCode =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "AddressDescription";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.AddressDescription = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.AddressDescription =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "AddressStreet";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.AddressStreet = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.AddressStreet =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "AddressZipCode";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.AddressZipCode = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.AddressZipCode =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "AddressCity";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.AddressCity = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.AddressCity =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "LanguageId";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.LanguageId = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.LanguageId =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "NAFCode";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.NAFCode = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.NAFCode =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "AddressCountryRegionId";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.AddressCountryRegionId = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.AddressCountryRegionId =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "InvoiceVendorAccountNumber";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.InvoiceVendorAccountNumber = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.InvoiceVendorAccountNumber =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "DefaultPaymentTermsName";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.DefaultPaymentTermsName = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.DefaultPaymentTermsName =

												null

										;
									}
									jsonPath_tExtractJSONFields_1 = "DefaultVendorPaymentMethodName";
									compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
											.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

									try {

										value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
												.read(row_tExtractJSONFields_1);

										row2.DefaultVendorPaymentMethodName = value_tExtractJSONFields_1 == null ?

												null

												: value_tExtractJSONFields_1.toString();
									} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
										globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
												e_tExtractJSONFields_1.getMessage());
										row2.DefaultVendorPaymentMethodName =

												null

										;
									}
								} catch (java.lang.Exception ex_tExtractJSONFields_1) {
									globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
											ex_tExtractJSONFields_1.getMessage());
									System.err.println(ex_tExtractJSONFields_1.getMessage());
									row2 = null;
								}

							}

							isStructError_tExtractJSONFields_1 = false;

//}

							tos_count_tExtractJSONFields_1++;

							/**
							 * [tExtractJSONFields_1 main ] stop
							 */

							/**
							 * [tExtractJSONFields_1 process_data_begin ] start
							 */

							currentComponent = "tExtractJSONFields_1";

							/**
							 * [tExtractJSONFields_1 process_data_begin ] stop
							 */
// Start of branch "row2"
							if (row2 != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								currentComponent = "tLogRow_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

									);
								}

///////////////////////		

								String[] row_tLogRow_1 = new String[15];

								if (row2.VendorGroupId != null) { //
									row_tLogRow_1[0] = String.valueOf(row2.VendorGroupId);

								} //

								if (row2.CUSIPIdentificationNumber != null) { //
									row_tLogRow_1[1] = String.valueOf(row2.CUSIPIdentificationNumber);

								} //

								if (row2.VendorAccountNumber != null) { //
									row_tLogRow_1[2] = String.valueOf(row2.VendorAccountNumber);

								} //

								if (row2.VendorOrganizationName != null) { //
									row_tLogRow_1[3] = String.valueOf(row2.VendorOrganizationName);

								} //

								if (row2.CurrencyCode != null) { //
									row_tLogRow_1[4] = String.valueOf(row2.CurrencyCode);

								} //

								if (row2.AddressDescription != null) { //
									row_tLogRow_1[5] = String.valueOf(row2.AddressDescription);

								} //

								if (row2.AddressStreet != null) { //
									row_tLogRow_1[6] = String.valueOf(row2.AddressStreet);

								} //

								if (row2.AddressZipCode != null) { //
									row_tLogRow_1[7] = String.valueOf(row2.AddressZipCode);

								} //

								if (row2.AddressCity != null) { //
									row_tLogRow_1[8] = String.valueOf(row2.AddressCity);

								} //

								if (row2.LanguageId != null) { //
									row_tLogRow_1[9] = String.valueOf(row2.LanguageId);

								} //

								if (row2.NAFCode != null) { //
									row_tLogRow_1[10] = String.valueOf(row2.NAFCode);

								} //

								if (row2.AddressCountryRegionId != null) { //
									row_tLogRow_1[11] = String.valueOf(row2.AddressCountryRegionId);

								} //

								if (row2.InvoiceVendorAccountNumber != null) { //
									row_tLogRow_1[12] = String.valueOf(row2.InvoiceVendorAccountNumber);

								} //

								if (row2.DefaultPaymentTermsName != null) { //
									row_tLogRow_1[13] = String.valueOf(row2.DefaultPaymentTermsName);

								} //

								if (row2.DefaultVendorPaymentMethodName != null) { //
									row_tLogRow_1[14] = String.valueOf(row2.DefaultVendorPaymentMethodName);

								} //

								util_tLogRow_1.addRow(row_tLogRow_1);
								nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

								row4 = row2;

								tos_count_tLogRow_1++;

								/**
								 * [tLogRow_1 main ] stop
								 */

								/**
								 * [tLogRow_1 process_data_begin ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_1 main ] start
								 */

								currentComponent = "tDBOutput_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row4"

									);
								}

								whetherReject_tDBOutput_1 = false;
								if (row4.VendorGroupId == null) {
									pstmt_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(1, row4.VendorGroupId);
								}

								if (row4.CUSIPIdentificationNumber == null) {
									pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(2, row4.CUSIPIdentificationNumber);
								}

								if (row4.VendorAccountNumber == null) {
									pstmt_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(3, row4.VendorAccountNumber);
								}

								if (row4.VendorOrganizationName == null) {
									pstmt_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(4, row4.VendorOrganizationName);
								}

								if (row4.CurrencyCode == null) {
									pstmt_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(5, row4.CurrencyCode);
								}

								if (row4.AddressDescription == null) {
									pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(6, row4.AddressDescription);
								}

								if (row4.AddressStreet == null) {
									pstmt_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(7, row4.AddressStreet);
								}

								if (row4.AddressZipCode == null) {
									pstmt_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(8, row4.AddressZipCode);
								}

								if (row4.AddressCity == null) {
									pstmt_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(9, row4.AddressCity);
								}

								if (row4.LanguageId == null) {
									pstmt_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(10, row4.LanguageId);
								}

								if (row4.NAFCode == null) {
									pstmt_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(11, row4.NAFCode);
								}

								if (row4.AddressCountryRegionId == null) {
									pstmt_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(12, row4.AddressCountryRegionId);
								}

								if (row4.InvoiceVendorAccountNumber == null) {
									pstmt_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(13, row4.InvoiceVendorAccountNumber);
								}

								if (row4.DefaultPaymentTermsName == null) {
									pstmt_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(14, row4.DefaultPaymentTermsName);
								}

								if (row4.DefaultVendorPaymentMethodName == null) {
									pstmt_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(15, row4.DefaultVendorPaymentMethodName);
								}

								pstmt_tDBOutput_1.addBatch();
								nb_line_tDBOutput_1++;

								batchSizeCounter_tDBOutput_1++;

								////////// batch execute by batch size///////
								class LimitBytesHelper_tDBOutput_1 {
									public int limitBytePart1(int counter, java.sql.PreparedStatement pstmt_tDBOutput_1)
											throws Exception {
										try {

											for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
												if (countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
													break;
												}
												counter += countEach_tDBOutput_1;
											}

										} catch (java.sql.BatchUpdateException e) {
											globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

											int countSum_tDBOutput_1 = 0;
											for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
												counter += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
											}

											System.err.println(e.getMessage());

										}
										return counter;
									}

									public int limitBytePart2(int counter, java.sql.PreparedStatement pstmt_tDBOutput_1)
											throws Exception {
										try {

											for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
												if (countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
													break;
												}
												counter += countEach_tDBOutput_1;
											}

										} catch (java.sql.BatchUpdateException e) {
											globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

											for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
												counter += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
											}

											System.err.println(e.getMessage());

										}
										return counter;
									}
								}
								if ((batchSize_tDBOutput_1 > 0)
										&& (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1)) {

									insertedCount_tDBOutput_1 = new LimitBytesHelper_tDBOutput_1()
											.limitBytePart1(insertedCount_tDBOutput_1, pstmt_tDBOutput_1);
									rowsToCommitCount_tDBOutput_1 = insertedCount_tDBOutput_1;

									batchSizeCounter_tDBOutput_1 = 0;
								}

								//////////// commit every////////////

								commitCounter_tDBOutput_1++;
								if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {
									if ((batchSize_tDBOutput_1 > 0) && (batchSizeCounter_tDBOutput_1 > 0)) {

										insertedCount_tDBOutput_1 = new LimitBytesHelper_tDBOutput_1()
												.limitBytePart1(insertedCount_tDBOutput_1, pstmt_tDBOutput_1);

										batchSizeCounter_tDBOutput_1 = 0;
									}
									if (rowsToCommitCount_tDBOutput_1 != 0) {

									}
									conn_tDBOutput_1.commit();
									if (rowsToCommitCount_tDBOutput_1 != 0) {

										rowsToCommitCount_tDBOutput_1 = 0;
									}
									commitCounter_tDBOutput_1 = 0;
								}

								tos_count_tDBOutput_1++;

								/**
								 * [tDBOutput_1 main ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_end ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_end ] stop
								 */

								/**
								 * [tLogRow_1 process_data_end ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_end ] stop
								 */

							} // End of branch "row2"

							// end for
						}

					} // C_01

					/**
					 * [tExtractJSONFields_1 process_data_end ] start
					 */

					currentComponent = "tExtractJSONFields_1";

					/**
					 * [tExtractJSONFields_1 process_data_end ] stop
					 */

				} // End of branch "row1"

				/**
				 * [tRESTClient_1 process_data_end ] start
				 */

				currentComponent = "tRESTClient_1";

				/**
				 * [tRESTClient_1 process_data_end ] stop
				 */

				/**
				 * [tRESTClient_1 end ] start
				 */

				currentComponent = "tRESTClient_1";

				if (globalMap.get("tRESTClient_1_NB_LINE") == null) {
					globalMap.put("tRESTClient_1_NB_LINE", 1);
				}

// [tRESTCliend_end]

				ok_Hash.put("tRESTClient_1", true);
				end_Hash.put("tRESTClient_1", System.currentTimeMillis());

				/**
				 * [tRESTClient_1 end ] stop
				 */

				/**
				 * [tExtractJSONFields_1 end ] start
				 */

				currentComponent = "tExtractJSONFields_1";

				globalMap.put("tExtractJSONFields_1_NB_LINE", nb_line_tExtractJSONFields_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tExtractJSONFields_1", true);
				end_Hash.put("tExtractJSONFields_1", System.currentTimeMillis());

				/**
				 * [tExtractJSONFields_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					int countSum_tDBOutput_1 = 0;
					if (pstmt_tDBOutput_1 != null && batchSizeCounter_tDBOutput_1 > 0) {

						for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
							if (countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
								break;
							}
							countSum_tDBOutput_1 += countEach_tDBOutput_1;
						}
						rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					}

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_1 = 0;
					for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_1 != null) {

					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");

				}
				resourceMap.put("statementClosed_tDBOutput_1", true);
				if (rowsToCommitCount_tDBOutput_1 != 0) {

				}
				conn_tDBOutput_1.commit();
				if (rowsToCommitCount_tDBOutput_1 != 0) {

					rowsToCommitCount_tDBOutput_1 = 0;
				}
				commitCounter_tDBOutput_1 = 0;
				conn_tDBOutput_1.close();
				resourceMap.put("finish_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tRESTClient_1 finally ] start
				 */

				currentComponent = "tRESTClient_1";

				/**
				 * [tRESTClient_1 finally ] stop
				 */

				/**
				 * [tExtractJSONFields_1 finally ] start
				 */

				currentComponent = "tExtractJSONFields_1";

				/**
				 * [tExtractJSONFields_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								ctn_tDBOutput_1.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tRESTClient_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final VendorsV3TD VendorsV3TDClass = new VendorsV3TD();

		int exitCode = VendorsV3TDClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = VendorsV3TD.class.getClassLoader()
					.getResourceAsStream("local_project/vendorsv3td_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = VendorsV3TD.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tRESTClient_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tRESTClient_1) {
			globalMap.put("tRESTClient_1_SUBPROCESS_STATE", -1);

			e_tRESTClient_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : VendorsV3TD");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 110912 characters generated by Talend Open Studio for Data Integration on the
 * 13 avril 2022 à 4:22:26 PM UTC−11:00
 ************************************************************************************************/