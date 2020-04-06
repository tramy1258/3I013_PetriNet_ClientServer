package clientserver;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PositionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.AnnotationGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.ArcHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.DimensionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NameHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NodeGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.OffsetHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PTMarkingHLAPI;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
import fr.lip6.move.pnml.framework.utils.exception.VoidRepositoryException;

public class Server1 {
	
	private PlaceHLAPI sv;
	private PlaceHLAPI[] sv_cl;
	private TransitionHLAPI[][] req_rep;
	private ArcHLAPI[][] arcs;
	
	public Server1(int id, Client1[] listclient, PageHLAPI serverpage, int x, int y) {		
		
		this.sv_cl = new PlaceHLAPI[listclient.length];
		this.req_rep = new TransitionHLAPI[listclient.length][2];
		this.arcs = new ArcHLAPI[listclient.length][4];
		try {

			sv = new PlaceHLAPI("sv"+id,serverpage);
			sv.setInitialMarkingHLAPI(new PTMarkingHLAPI(1L));
			NodeGraphicsHLAPI pg = new NodeGraphicsHLAPI(sv);
			PositionHLAPI pos = new PositionHLAPI(x,y,pg);
			DimensionHLAPI dim = new DimensionHLAPI(25,25,pg);
			OffsetHLAPI o = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(sv.getId(),sv)));
			
			for (int i=0; i<listclient.length; i++) {
				sv_cl[i] = new PlaceHLAPI("sv"+id+"_c"+listclient[i].get_clientid(),serverpage);
				NodeGraphicsHLAPI pg1 = new NodeGraphicsHLAPI(sv_cl[i]);
				PositionHLAPI pos1 = new PositionHLAPI(x,y+50+50*i,pg1);
				DimensionHLAPI dim1 = new DimensionHLAPI(25,25,pg1);
				OffsetHLAPI o1 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(sv_cl[i].getId(),sv_cl[i])));
				
				req_rep[i][0] = new TransitionHLAPI("req"+id+"_c"+listclient[i].get_clientid(),serverpage);	
				NodeGraphicsHLAPI pg2 = new NodeGraphicsHLAPI(req_rep[i][0]);
				PositionHLAPI pos2 = new PositionHLAPI(x-40-30*i,y+30+40*i,pg2);
				DimensionHLAPI dim2 = new DimensionHLAPI(10,25,pg2);
				OffsetHLAPI o2 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(req_rep[i][0].getId(),req_rep[i][0])));
				
				req_rep[i][1] = new TransitionHLAPI("rep"+id+"_c"+listclient[i].get_clientid(),serverpage);
				NodeGraphicsHLAPI pg3 = new NodeGraphicsHLAPI(req_rep[i][1]);
				PositionHLAPI pos3 = new PositionHLAPI(x+40+30*i,y+30+40*i,pg3);
				DimensionHLAPI dim3 = new DimensionHLAPI(10,25,pg3);
				OffsetHLAPI o3 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(req_rep[i][1].getId(),req_rep[i][1])));
				
				arcs[i][0] = new ArcHLAPI("sva0of"+id+"_c"+i,sv           ,req_rep[i][0],serverpage);
				arcs[i][1] = new ArcHLAPI("sva1of"+id+"_c"+i,req_rep[i][0],sv_cl[i]     ,serverpage);
				arcs[i][2] = new ArcHLAPI("sva2of"+id+"_c"+i,sv_cl[i]     ,req_rep[i][1],serverpage);
				arcs[i][3] = new ArcHLAPI("sva3of"+id+"_c"+i,req_rep[i][1],sv           ,serverpage);
			}
			
			//System.out.println("Server "+id+" created");
					
		} catch (InvalidIDException e) {
			System.out.println("InvalidIDException caught by while creating server");
			e.printStackTrace();
		} catch (VoidRepositoryException e) {
			System.out.println("VoidRepositoryException caught by while creating server");
			e.printStackTrace();	
		}
	}
	
	public TransitionHLAPI get_req (int indlistclient) {
		return req_rep[indlistclient][0];
	}
	
	public TransitionHLAPI get_rep (int indlistclient) {
		return req_rep[indlistclient][1];
	}
}
