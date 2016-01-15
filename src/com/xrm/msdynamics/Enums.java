package com.xrm.msdynamics;

/**
 *
 * @author rguluarte
 */
public class Enums {

    public class ActivityTypeCode {

        public static final int Fax = 4204;
        public static final int PhoneCall = 4210;
        public static final int Email = 4202;
        public static final int Letter = 4207;
        public static final int Appointment = 4201;
        public static final int ServiceAppointment = 4214;
        public static final int OpportunityClose = 4208;
        public static final int OrderClose = 4209;
        public static final int QuoteClose = 4211;
        public static final int CaseResolution = 4206;
        public static final int Task = 4212;
        public static final int CampaingResponse = 4401;
        public static final int CampaingActivity = 4402;
        public static final int BulkOperation = 4406;
        public static final int RecurringAppointment = 4251;
    }

    public class MessageName {

        public static final String Create = "Create";
        public static final String Delete = "Delete";
        public static final String Update = "Update";
        public static final String SetState = "SetState";
        public static final String SetStateDynamic = "SetStateEntity";
        public static final String Disassociate = "Disassociate";
    }

    public class ParameterName {

        public static final String Target = "Target";
        public static final String Id = "id";
        public static final String Relationship = "Relationship";
        public static final String State = "State";
        public static final String EntityMoniker = "EntityMoniker";
        public static final String RelatedEntities = "RelatedEntities";
    }

    public class AsyncOperationState {

        public static final int Completed = 3;
        public static final int Locked = 2;
        public static final int Ready = 0;
        public static final int Suspended = 1;

    }

    public class AsyncOperationStatus {

        public static final int CompletedSucceeded = 30;
        public static final int CompletedFailed = 31;
        public static final int CompletedCanceled = 32;
        public static final int LockedInProgress = 20;
        public static final int LockPausing = 21;
        public static final int LockCanceling = 22;
        public static final int ReadyWaitingForResources = 0;
        public static final int SuspendWaiting = 10;
    }

    public class AddressType {

        public static final int Bill = 0;
        public static final int Ship = 1;
        public static final int Other = 2;

    }

    public class ContactState {

        public static final int Active = 0;
        public static final int Inactive = 1;

    }

    public class ContactStatus {

        public static final int Active = 1;
        public static final int Inactive = 2;

    }

    public class PurshaseTimeframe {

        public static final int Immediate = 0;
        public static final int ThisQuarter = 1;
        public static final int NextQuarter = 2;
        public static final int ThisYear = 3;
        public static final int Unknown = 4;

    }

    public class PurchaseProcess {

        public static final int Individual = 0;
        public static final int Committee = 1;
        public static final int Unknown = 2;
    }

    public class LeadState {

        public static final int Open = 0;
        public static final int Qualified = 1;
        public static final int Disqualified = 2;

    }

    public class LeadStatus {

        public static final int New = 1;
        public static final int Contacted = 2;
        public static final int Qualified = 3;
        public static final int Lost = 4;
        public static final int CannotContact = 5;
        public static final int NoLongerInterested = 6;
        public static final int Canceled = 7;

    }

    public class ProductStructure {

        public static final int Product = 1;
        public static final int ProductFamily = 2;
        public static final int ProductBundle = 3;

    }

    public class EmailPriority {

        public static final int Low = 0;
        public static final int Normal = 1;
        public static final int High = 2;
    }

    public static class EntityName {

        public static final String Contact = "contact";
        public static final String Lead = "lead";
        public static final String Product = "product";
        public static final String Order = "salesorder";
        public static final String OrderClose = "orderclose";
        public static final String OrderProduct = "salesorderdetail";
        public static final String Opportunity = "opportunity";
        public static final String OpportunityClose = "opportunityclose";
        public static final String OpportunityProduct = "opportunityproduct";
        public static final String Shopify = "xrm_shopify";
        public static final String CustomerAddress = "customeraddress";
        public static final String Annotation = "annotation";
        public static final String PriceList = "pricelevel";
        public static final String Currency = "transactioncurrency";
        public static final String Unit = "uom";
        public static final String UnitGroup = "uomschedule";
        public static final String Account = "account";
        public static final String Email = "email";
        public static final String ActivityParty = "activityparty";
        public static final String SystemUser = "systemuser";
        public static final String ProductSubstitute = "productsubstitute";
        public static final String Subject = "subject";
        public static final String DuplicateDetectionRule = "duplicaterule";
        public static final String Campaign = "campaign";
        public static final String EmailTemplate = "template";
        public static final String SharePointDocument = "sharepointdocument";
        public static final String Article = "";
        public static String BusinnesUnit;
        public static final String Organization = "organization";
        public static final String Appointment = "appointment";
        public static final String LiveHiveAction = "xrm_livehiveaction";
        public static final String Task = "task";
        public static final String View = "savedquery";
        public static final String SavedView = "userquery";
        public static final String Report = "report";
    }

    public static class EntityPluralName {

        public static final String Contact = "Contacts";
        public static final String Lead = "Leads";
        public static final String Order = "Orders";
        public static final String Opportunity = "Opportunities";
        public static final String Product = "Products";
    }

    public static class EntityDisplayName {

        public static final String Contact = "Contact";
        public static final String Lead = "Lead";
        public static final String Order = "Order";
        public static final String Opportunity = "Opportunity";
        public static final String Product = "Product";
    }

    public static class FreightTerms {

        public static final int FOB = 1;
        public static final int NoCharge = 2;
    }

    public static class ShippingMethodCode {

        public static final int Airbone = 1;
        public static final int DHL = 2;
        public static final int FedEx = 3;
        public static final int UPS = 4;
        public static final int PostalMail = 5;
        public static final int FullLoad = 6;
        public static final int WillCall = 7;
    }

    public static class PaymentTermsCode {

        public static final int Net30 = 1;
        public static final int TwoPercent10Net30 = 2;
        public static final int Net45 = 3;
        public static final int Net60 = 4;
    }

    public static class Gender {

        public static final int Male = 1;
        public static final int Female = 2;
    }

    public static class FamilyStatusCode {

        public static final int Single = 1;
        public static final int Married = 2;
        public static final int Divorced = 3;
        public static final int Windowed = 4;
    }

    public static class PreferedContactMethod {

        public static final int Any = 1;
        public static final int Email = 2;
        public static final int Phone = 3;
        public static final int Fax = 4;
        public static final int Mail = 5;
    }
}
