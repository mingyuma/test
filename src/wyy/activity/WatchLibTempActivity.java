package wyy.activity;

import wyy.utils.Constants;

import com.example.imagemanager6.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class WatchLibTempActivity extends Activity {

	private TextView title;
	private TextView content;
	private String xinwen0 = "中华医学会第十六届骨科学术会议暨第九届COA国际学术大会于2014年11月20～23日在北京国家会议中心隆重召开。陈鸿医生带来了陈旧性肩关节前脱位的治疗分析的报告。     背景：肩关节脱位好超过3周未复位时称为陈旧性肩关节脱位，慢性难复性肩关节脱位多见于前脱位;肩关节脱位的并发症较多：骨折、血管、臂丛神经损伤;肩关节脱位治疗方法：闭合复位、切开复位、关节置换、关节融合及姑息治疗。方法： 我们采用方法;切开复，肱骨骨折处复位内固定，盂唇关节囊复合体修复和/或lartajet手术修复肩胛盂前方骨缺损。我院自2012年月至2013年10月共收治陈旧性肩关节脱位患者8例，均采用切开复位手术治疗。其中男性6例，女性2例;年龄最小16岁，最大58岁，平均脱位时间7.3周(5～9周)，均为摔伤，1例受伤前有酗酒史。每例均伴有肱骨Hill-sachs损伤，肩胛盂前缘均有骨性缺损，其中7例合并有肱骨大结节骨折，一例合并有锁骨骨折，一例合并有臂丛损伤表现，主要有正中神经和尺神经支配区感觉麻木症状。肩关节活动明显受限，外展均<60度，外旋<30度，内旋<20度。所有脱位肩关节均无法闭合复位。手术方法：全麻，沙滩椅体位，手术入路：肩关节前内侧入路。松解关节腔：用Trocar先将关节腔的粘连和纤维化组织松解，尤其注意松解后关节囊，再将肱盂关节复位。复位固定肱骨头的骨折：大多数为肱骨大结节骨折;复位肱骨大结节骨折，用空心钉固定，减小肱骨近端骨折缺损范围。盂唇关节囊复合体修复：修复前方盂唇关节囊复合体(锚钉或穿孔)，紧缩关节囊。肩胛盂前方骨性缺失：lartajet手术修复肩胛盂前方骨缺损。肩关节克氏针固定：克氏针穿关节固定2-3周，防止术后早期的再脱位。结果：术后均获得随访，平均随访时间9.6月(8～14月)，Rowe评分平均增加59分(P>0.05)。UCLA评分由术前平均11.5分，终末随访为平均28.6分。平均满意8分(0到10分)。结论：陈旧性肩关节前脱位目前在临床上十分少见，多因为治疗延误所引起，其具有难复位，复位后难稳定，肩关节功能恢复差的特点。我们针对其损伤特点采用切开复位，利用修补盂唇关节囊或lartajet手术及修复肱骨近端骨缺损治疗陈旧性肩关节前脱位的方法，能有效恢复肩关节的稳定性，避免再次脱位出现，手术效果可靠。";
	private String xinwen1 = "医学论坛网讯(通讯员周军 范嵘 记者程守勤)近日，华东首例、全球首批第3例神经再生胶原支架复合间充质干细胞手术移植治疗脊髓损伤病例在苏州大学附属第一医院骨科完成，标志着该治疗方法已进入临床研究阶段，向攻克脊髓损伤治疗这一世界难题迈出坚实的一步。脊髓是中枢神经的重要组成部分，主要功能是传送脑与外周神经之间的神经信息，同时也是许多简单反射活动的低级中枢。严重的脊髓损伤往往导致损伤平面以下的长期感觉、运动及括约肌功能障碍，即损伤平面以下没有感觉、不能运动、大小便不能自己控制。据统计我国每百万人口的脊髓损伤发病率达到 40-60人次/年，据估算我国因脊髓损伤导致瘫痪的患者超过百万人。脊髓损伤患者以青壮年为主，这些青壮年作为创造社会价值的重要人群，在脊髓损伤后其劳动能力几乎完全丧失，加上脊髓损伤患者一生的治疗康复费用相当高昂，给家庭和社会均造成巨大负担。而且，目前脊髓损伤尚无有效的治疗方法，是医学领域面临的重大难题。中国科学院遗传与发育生物学研究所戴建武教授团队历经十余年研究，研制出基于胶原蛋白的神经再生支架，可减少脊髓损伤部位瘢痕的形成，引导神经生长;结合间充质干细胞，可分泌生长因子和调节损伤后的免疫反应促进神经细胞存活，减低机体免疫反应。该方法已在包括大鼠和比格犬的长距离脊髓完全横断损伤动物实验中获得成功。此次由苏州大学附属第一医院骨科杨惠林教授团队采用戴建武教授团队研发的神经再生胶原支架复合自体骨髓间充质干细胞手术移植治疗的脊髓损伤患者，是华东首例，全球第3例。这一移植手术属于该治疗方法的临床研究的第一阶段，主要目的为评估该治疗方法的手术安全性，并完善治疗方案，为进一步的临床应用奠定基础。首批计划治疗脊髓损伤患者5至6人，将由位于苏州的苏州大学附属第一医院、位于北京的解放军总医院第一附属医院和位于天津的中国武警脑科医院三家医院共同完成。目前，该临床研究在上述医院均通过伦理委员会审查批准并按国家相关规定在上级卫生主管部门备案。 首批患者手术完成后，研究团队将对患者进行长达1年的康复训练并对疗效进行综合分析。在此基础上改进治疗方案，之后将进行更大规模的临床研究。";
	private String xinwen2 = "中华医学会第十六届骨科学术会议暨第九届COA国际学术大会于2014年11月20～23日在北京国家会议中心隆重召开。中南大学湘雅二医院脊柱外科马泓医生做了研究报告，内容：颈前路内窥镜下松解复位后路内固定治疗难复性寰枢关节垂直脱位。方法：回顾性分析了2010年3月到2012年7月，5例难复性寰枢关节垂直脱位的患者，男性1例，女性4例，平均年龄50.4岁(34～63岁)，均行全麻下颅骨牵引复位失败。经颈前路内镜松解复位术：Smith-Robinson入路;显露并切开椎前筋膜;导入内镜工作系统;内镜直视下清除寰枢关节间的疤痕组织及异常骨性连接组织;充分松解后，应用刮匙撬拔、牵引使寰枢复位。结果：平均随访时间为25.8月(12～40月)，前路松解平均手术时间为63±11.71分钟(52～83分钟)，后路植骨内固定平均时间为78±14.59分钟(66～106分钟)，平均出血量为316±96.04ml(230～500ml)，所有病例均获得寰枢关节解剖复位。结论：经颈前路内窥镜下松解复位后路内固定治疗难复性寰枢关节垂直脱位安全有效;需要正确认识前路经口、经鼻、经颈入路手术方式的优缺点。经颈入路的优点：入路切口清洁无菌;解剖更为脊柱外科医生熟悉;内镜视野下操作更加精细。经颈入路的缺点：操作角度、范围相对较小;不适用肥胖、严重后凸畸形患者。";
	private String xinwen3 = "颈椎有正常的生理弯曲，如果没有生理弯曲，甚至向相反的方向弯曲，称为反弓。颈椎反弓是构成颈椎病最常见的病理基础，而不适当用枕是导致颈椎反弓的重要原因。颈椎反弓（比强直更为严重的颈椎疾病）相比强直的颈椎，反弓的颈椎曲度是个反C型，而正常曲度应该是正C型。颈椎有正常的生理弯曲，如果没有生理弯曲，甚至向相反的方向弯曲，称为反弓。人类属于高级脊椎动物，颈椎“C”形向前的生理弧线保证了颈椎活动的高度灵活性，但人们常常发生违背颈椎生理曲线的姿态和活动。原因最常见的就是“高枕”,高枕使头部前屈，增大下位颈椎的应力，有加速颈椎退变的可能。（现在年轻化，包括长时间伏案工作是最容易出现类似病症）？反弓高枕静卧看书、卧高靠背看电视以及长时间上网、搓麻等不良的生活习惯，长时间折服着颈椎“瘦弱”的连结，导致其曲线前凸日渐减少，变直甚或反弓。而与颈椎相关的骨关节组织因挤压提早产生增生、骨刺、韧带肥厚、椎间盘突出而压迫颈部脊髓、神经根、椎动脉等产生一系列生理病理变化，出现颈椎病相关的临床症状，如项肩臂痛、头晕、、失眠、健忘等等。颈椎反弓会引起很多疾病。首先是颈椎动脉受压，会引起大脑供血不足，其次是神经受压，产生神经根性病变或交感、神经系病。常见肢体麻木、恶心呕吐、头晕头痛，严重的还会引起瘫痪。我国颈椎病近十年来发病率呈直线上升趋势。据不完全统计，全世界每天近一亿人发生颈肩臂痛，该症状群已成为骨科门诊最常见的问题。“颈椎反弓”现象在普通X光片甚至医生的体检中都很容易被发现，近年来逐渐呈低龄化趋势。曾发现6岁颈椎就出现反弓的患儿，想必这一现象在中、小学生亦不在少数，严重影响了青少年的健康发育，应引起老师和家长的高度重视。预防（其中关于颈椎操的练习不适合已经发生颈椎强直和反弓的患者练习，若颈椎在不正确的状态下去工作，可能导致颈椎体的磨损等问题）"
			+ "　一、平时习惯。应该养成良好的生活、学习习惯，不卧床看书报、看电视，掌握正确的写字姿势。二、注意用枕。用枕不应过高。 有侧卧睡姿者睡前将枕头塑形成中凹状，枕头分成三等分，两边可高出一些，以适应侧卧时颈椎的正常生理曲线。 枕芯材料以芦花或质地稍硬的苇蕊、绿豆壳为好，不提倡使用过软的海绵枕或夏季常用的过硬的竹枕等。三、多活动。长期伏案工作者，每小时应有5—10分钟的休息时间，并作颈椎自我保健操。 加强颈肩部肌肉的锻炼，在工间或工余时，做头及双上肢的前屈，后伸及旋转运动，临床上称之为“米”字操。 既可缓解疲劳，又能使肌肉发达，韧度增强，从而有利于颈段脊柱的稳定性，增强颈肩顺应颈部突然变化的能力。四、勤锻炼。适当参加力所能及的健康的体育活动，如羽毛球、倒退行走等，以改善颈椎供血，防止和延缓颈椎退行性变的发生年龄。五、常咨询。如经常出现“落枕”或颈肩臂痛，转头性眩晕，应及时到骨科医生处就医确诊，切忌盲目推拿而留下后患。六、康复。颈椎病康复操可改善患者颈部的血液循环，松解粘连和痉挛的软组织。颈椎病康复操中不少动作对颈椎病有独特疗效；无颈椎病者可起到预防作用。姿势：两脚分开与肩同宽，两臂自然下垂，全身放松。两眼平视，均匀呼吸，站坐均可。1.双掌擦颈十指交叉贴于后颈部，左右来回摩擦100次。2.左顾右盼头先向左后向右转动，幅度宜大，以自觉酸胀为好，30次。七、颈椎病的治疗以保守疗法为主，常用的方法有牵引、理疗、按摩、针灸和药物治疗等。其中牵引疗法是一种较为有效易行的方法，对多数颈椎病患者有效。关于治疗在确保颈椎没有发生炎症，钙化，骨质增生，椎间盘移位，突出等情况可采用脊椎矫正复位。若颈椎疼痛神经麻木，必要时须找专科医院就医解决。";
	private String xinwen4 = " 病人术前形成的深静脉血栓(DVT)在手术中可能由于多种原因脱落，从而引起肺栓塞(PE)，导致病人出现生命危险，因此引起医学界越来越多关注。但这种发生率高、危险性强的术前DVT可以通过进行双下肢深静脉多普勒彩色超声检查或者有创性检查下肢血管造影来确诊，并根据结果给与抗凝治疗或放置下腔静脉滤器等方法预防及治疗，属于可预防的死因。由于造影检查属有创检查且成本高昂，抗凝药物易引起多种并发症，其对绝对风险降低的幅度，可能无法抵消不良事件发生的风险或额外费用的升高，因此临床上并不推荐对所有术前病人均无差别的进行DVT治疗。但哪类患者属于高危人群，需要接受检查和治疗，目前医师之间往往存在较大的分歧。医师在决定DVT方面的预防诊断和治疗方案时，大多基于对于患者的血栓危险程度的判断。这种判断方法主观性过强，尤其在患者同时具有多个危险因素的时候，如何对各种危险因素带来的DVT风险大小进行权衡及叠加，依旧很大程度上依赖于临床医师的个人经验。因此，根据未来发生DVT的风险大小把患者进行分层，并选择相应获益最大的群体加以预防以及诊断，保证最优的“风险-成本-效益”关系，就成为术前DVT预防以及治疗的关键。为了将医生的临床经验量化，在不增加病人医疗费用和有创检查的情况下，给临床医师DVT的初筛工作提供有效的参考，协助其将患者的多种危险因素整合成一个直观的分数，北京积水潭医院创伤骨科围手术期并发症项目研究组，针对2012年以来因创伤接受手术治疗并住院的所有患者为对象，进行了新鲜下肢骨折术前血栓危险程度评分量表的相关探索，并初步得出灵敏度和特异性均较令人满意的评估量表。量表发现，年龄、入医院到接受手术间的时间，受伤原因的能量高低(高能量指受伤因素中含有高能量释放于组织的一中损伤，如高处坠落为高能量伤，而摔伤与扭伤则为低能量)，受伤部位，病人是否有心脑血管疾病史，入院时D-二聚体 (D-Dimer)检查值等均会影响病人的血栓发生率。本研究对以上危险因素进行了多因素Logistic回归分析，得出独立影响因素及每个因素的OR值，并根据OR值四舍五入赋分，得出DVT危险性评分量表。 在此评分量表的指导下，医师可快速的初步判断出患者是否具有较高的血栓发生概率，针对危险性较高者给予抗凝等其他血栓预防治疗，而不须对所有病人都进行无差别的有创检查及药物或手术治疗，从而缩减了医院的医疗成本，有效地挽救了病人的生命，减少了病人的痛苦，并为其节省下不菲的医疗费用。 此量表的推广可帮助DVT预防经验较少的年轻医师，医疗资源相对紧张的地方医院，提高对病人血栓发生率的预测，更准确、更高效的避免DVT对病人的生命造成危险，降低医疗资源的浪费。 此项研究论文已获得中华医学会第十六届骨科学会荣誉中青年医师优秀论文二等奖。研究组将在未来继续对该领域加深分析探索，以期在国内DVT术前诊治方面起到标杆示范作用。";
	private String xinwen5 = "【2015.3.26日讯】据一项大型队列研究发现，患有明显髋关节骨性关节炎(RHOA)的老年女性，其心血管疾病(CVD)风险死亡率与总体死亡率要高于同等年龄下非患此疾病者。来自乔治亚州佐治亚疾病控制与预防中心(CDC's)关节炎项目组流行病学专家Kamil E. Barbour博士(公共卫生硕士)说道，“需特别指出的是，RHOA与43%的总死亡率相关，与25%的CVD死亡率相关。”该结论发表在3月10日的《关节炎风湿病》杂志。研究者们表示，RHOA的物理功能与缺乏身体活动导致死亡率风险增加。Barbour 博士对媒体表示，“肢体活动增加意味着物理功能的改善，大量的文献表明功能障碍是死亡率增加的风险因素。”“关节炎患者的活动减少，临床医生应该建议这些患者多活动，并对其干预。CDC指南建议增加多种肢体活动，以及自我管理干预，这对功能的提高大有裨益。”马萨诸塞州波士顿大学流行病医学研究与康复中心的医学与流行病学博士Yuqing Zhang参与了该项研究，他对媒体表示，这些研究发现非常有趣，其它研究也发现膝关节炎与髋关节炎与总死亡率相关。 作者们利用新的统计学方法对几种潜在生物机制进行研究，即哪种髋关节炎不仅影响总死亡率，也影响特定死亡率，值得鼓励。这些结果表明，髋关节骨性关节炎对总死亡率和和CDV死亡率的影响主要通过对物理功能的作用(分别为42.9%和25%)实现，这一点不仅促进了对髋关节骨性关节炎的病理生理学理解，也为健康护理者提供了循证医学证据，找到减少髋关节骨性关节炎死亡率的根本原因。研究发现，骨关节炎影响的是死亡率，而不是患病率Barbour 医生说，“最初，骨性关节炎被认为与患病率相关，但是我们的研究显示，患有该病可增加总死亡率和CVD死亡率。不仅如此，由于这类患者活动受限，上述死亡结果的风险发生率也是略高一筹。” 中位数分析显示，物理功能受限导致总体死亡率增加43%，心血管疾病死亡率增加25%。Barbour医生说，“在我们的研究中，髋关节骨性关节炎是心血管病死亡率的独立风险因素，与物理活动受限相关。换句话说，大部分此病患者死亡是因为他们的活动受限。”全髋关节置换可产生保护性作用有趣的是，当研究者们将其研究严格限定在未接受全髋关节置换术的RHOA成年患者时，总死亡率与CVD死亡率的风险比增加，且超出那些接受置换术的RHOA成人患者(分别是1.24 对比 1.14，1.24 对比 1.30)。Barbour说道，“明显保护作用的原因尚且不清，但是一些研究，也包括我们的研究，发现全髋关节置换可降低死亡风险。有人猜测是因为置换术可提高大多数病例中患者的活动功能，而我们的研究恰好证明了死亡风险来自活动受限。”";
	private String xinwen6 = "【2015.2.24日讯】据密苏里州的研究人员称，对前臂骨折儿童进行复位，注入氯胺酮五秒左右即可达到充足镇静目的。该报道于2月17日在线发表于《医景》(Medscape)。来自华盛顿大学圣路易医学院的Sri S. Chinta博士和同事们的研究于1月14日收录于《急诊医学年鉴》，他指出此类传统儿童镇静剂应单次1-2 mg/kg缓慢给药。 典型给药时间在30秒至60秒之间，可持续五至十分钟。不过，苏醒时间需要110分钟。在寻找起效更加迅速的方法中，该研究团队使用了升降法对静脉输注氯胺酮五秒左右的药物剂量进行了评估。结果显示，有效剂量(ED50)与(ED95)在2至17岁的儿童中适用。 在年龄2至5岁的20位受试者中，ED50与ED95的评估值为0.7 mg/kg。对于6至11岁的儿童，对应的剂量为0.5 mg/kg与0.7 mg/kg。在其他的20名儿童中(年龄在11岁至17岁)，他们的剂量为0.6 mg/kg与0.8 mg/kg。数据回顾之后，在最年轻的患者中，研究者继续使用0.8 mg/kg ED95来评估有效镇静(而不是0.7 mg/kg)。利用ED95剂量在另外一组中成功实现有效镇静。单剂氯胺酮总体镇静时间分别为25、22.5和25分钟。当使用附加剂量时，总体镇静时间分别为35、25和45分钟。在全部试验中，未出现主要不良事件，其中包括三个年龄组中个出现一例患者出现呕吐反应。不良事件和发生频率被研究者称为“似乎与先前已被报告的研究具备可比性。”尽管该研究方法具有明显的成功之处，该研究团队强调“我们也许未能概括其它疼痛手术与不同人群的ED50与ED95。”在某个评论中，来自孟菲斯田纳西非大学健康科学中心的Jay博士观察到“还需要更多的对比性试验来确定该方法在改良术中的安全性、有效性以及临床复苏时间。”一封邮件如此评论：“该研究在初始阶段的设计新颖，并获得成功。本研究旨在对青少年儿童找到缩短氯胺酮滞留的有效剂量。”来自温哥华英国哥伦比亚大学的Gary博士如是说。该试验由Andolfatto博士指导ED镇静的试验设计，他补充道，“单次快速小剂量氯胺酮给药的精确性极具意义，快速输注高水平的氯胺酮能够达到有效镇静、重建机体并实现快速恢复。”“在更大面积的试验中，还需要考虑重要安全性，但该方法极具前景。”他补充道。这一研究结论在三次学术会议上公布，最后一次是在2014年10月份举行的美国儿科协会学术会议上。本研究的部分赞助来自国家健康协会和美国儿科学会。作者声明无利益关系。";
	private String xinwen7 = "所谓封闭针，是将一定浓度和容量的强的松龙注射液（所用的强的松龙，还有同类药物：地塞米松、倍他米松等）和盐酸普鲁卡因（或它们的同类药物）混合，注射到病变区域。肌肉、关节、筋膜、肌腱旁以至椎管内等处都可以进行封闭注射。（1）强的松龙属人工合成激素，能够改善毛细血管通透性，抑制炎症反应，减轻致病因子对机体的损害；盐酸普鲁卡因是一种局部麻醉药，拔牙、做小手术时局部麻醉就是用的它，它可以缓解疼痛，增强疗效。有时也用它的同类物利多卡因，作用差不多。医院骨科副主任医师傅捷说，局麻药的作用为暂时阻断局部神经传导，使这些神经支配的相应区域产生麻醉作用，从而缓解疼痛。激素类药物局部注射是治疗软组织慢性损伤最常用的行之有效的方法，国内使用这一疗法已有40余年。大多都是骨科的病人才需要打封闭针，如肩周炎、关节炎、腱鞘炎、腰肌劳损、腰椎间盘突出症等，虽然它们的发病机理不一样，但都有一个共同的特点：即软组织损伤和无菌性炎症，为此，医生常常选用封闭疗法，也就是常说的打“封闭针”.封闭疗法是医生通过对病人进行检查，找到局部压痛点后，将药物注射到疼痛的部位，达到消炎、止痛的目的，并有缓解局部肌肉紧张的作用。时间长的可管一年以上，短的数周，视病情轻重，时间长短不定。这是一种对症治疗措施，对消除局部的疼痛症状有较好的效果。封闭疗法是骨伤科使用的一种常用方法，有的病人一听说打“封闭针”,就会产生惧怕心理，总认为“封闭针”只能暂时减轻疾病症状，并不能从根本上解决问题。还怕打了“封闭针”后，从此一直要打下去，不能停下来，还担心有副作用。傅主任说，激素药并没有成瘾性。但长期应用可产生习惯性或依赖性，但这两种情况均在长期、大剂量、反复应用激素后才可能会出现。而医生注射封闭针一般根据病情封闭一次或几次，每次间隔7~10天或一个月，一般连续不超过3~4次。如需继续注射，间隔时间很长，所用的剂量也很小。所以，没有“老是要打”这一说法，对人体极少会产生副作用。这种疗法有给药直接、简单安全、疗效迅速、不住院、价格低、副作用少、患者易于接受等优点。当然，打“封闭针”时疼痛并不明显，有的在注射1~2天后局部疼痛可能略有加重，这与药品刺激、局部压力增高有关，但很快就会消失。一旦选用封闭疗法还应坚持治疗，完成治疗疗程，以免中途停止，达不到应有的疗效。虽然封闭疗法不能从根本上去除病因，但能起到减轻甚至消除症状、预防并发症的作用。许多颈肩腰腿疼痛症经过一次或几次封闭治疗后，症状完全消失且不再复发。因此，不能绝对地认为封闭治疗不能除病根。傅主任提醒说，为了防止感染，封闭注射应在正规医院进行，根据病情严格掌握剂量。患有糖尿病的病人，打封闭针后可能会出现血糖一次性升高，如糖尿病病人近期血糖不稳定，可在调整稳定后再进行封闭治疗。需要注意的是，医生在对患者进行封闭治疗时应无菌操作，注射前要在洁净皮肤部位严格消毒，注射部位要准确，用药选择要恰当，注射后要观察15分钟，防止过敏反应和其他反应，封闭后3天内注意保持皮肤清洁，防止污染。当然，封闭治疗只是将两种药混合在一起对人体进行注射，与其他药物一样，并不是什么“灵丹妙药”.有些情况下可能效果欠佳，必要时医生可配合其他方法如牵引、推拿、理疗、佩戴腰围、内外用药等，必要时改用手术方法。（2）药物封闭疗法就是我们常说的打封闭，适用于急性或慢性软组织损作，非化脓性炎症，如腰肌劳损、肩周炎、腱鞘炎、肌筋膜炎等。常用的药物为普鲁卡因加醋酸强的松龙或醋酸氢化可的松局部痛点注射，需要注意的是：1、切不可将醋酸可的松用于局部封闭，因醋酸可的松无局部作用，药液吸收后可出现全身反应，因此封闭前应仔细核对药液。2、醋酸强的松龙作局部封闭时，用量不要太大，间隔时间不能太短，一般剂量为每次12.5-25毫克，每隔5-7天封闭1次，3-4次为一个疗程，最多不超过两个疗程，否则药液在局部积聚，抑制纤维组织形成，使局部组织脆弱。3、封闭注射后，由于药物反应，局部可出现肿胀疼痛，一般48小时后可缓解并消失，如果72小时后仍有红肿，发热，应考虑是否有急性化脓性感染。（3）在打封闭前应先掌握封闭部位的局部解剖。比如你这针下去多深会到骨骼，多深会在肌键，这个部位有没有重要的器官，大的血管，神经以及是否注射到了关节腔等。同时你的手下感觉也很重要，如穿过肌筋膜的“突破感”等。还有在注射时要告知患者，但出现局部剧烈疼痛或放射样疼痛时要说声，因为你很可能刺到了神经或血管，应该立即改变针的方向。在就是注射药物前一定要回吸注射器，看有无回血。药物入血还是有一定的危险性。安全第一！总之，打封闭要做到“胆大心细”.切不可什么也不管，哪痛就扎哪！";

	private String gongshi0 = "胫骨平台骨折是指骨折线累及胫骨近端关节面"
			+ "的骨折，为关节内骨折。由于胫骨平台是重要的负荷" + "结构，周围解剖结构复杂、损伤机制各异、骨折形态不"
			+ "一，且多伴有不同程度的软组织损伤，诊断治疗难度" + "大。处理不当易于出现筋膜间隔综合征、膝关节周围"
			+ "皮肤软组织织坏死、感染、骨不连、关节畸形、不稳定、" + "创伤性关节炎、关节僵硬等诸多并发症，严重影响患"
			+ "者站立、行走功能。目前对于胫骨平台骨折的诊断和" + "治疗还存在诸多争议。为规范胫骨平台骨折的诊断和"
			+ "治疗，帮助创伤骨科医生正确处理胫骨平台骨折，特" + "根据胫骨平台骨折患者的临床特点，基于多中心研究"
			+ "结果和国内外研究进展，重点围绕术前评估、诊断、治" + "疗及术后康复，编写了“胫骨平台骨折诊断与治疗的"
			+ "专家共识”，提出胫骨骨平台骨折规范合理的诊疗建" + "议。";

	private String lujing0 = "（一）适用对象。第一诊断为闭合性肱骨干骨折（ICD-10:S42.301）行肱骨干骨折内固定术（ICD-9-CM-3：78.52/79.11/79.31）。（二）诊断依据。根据《外科学（下册）》（8年制和7年制临床医学专用教材，人民卫生出版社，2005年8月第1版）。1.病史：外伤史。2.体格检查：患肢肿胀、疼痛、活动受限、畸形、反常活动等。3.辅助检查：X线检查发现肱骨干骨折。（三）选择治疗方案的依据。根据《外科学（下册）》（8年制和7年制临床医学专用教材，人民卫生出版社，2005年8月第1版）。1.年龄在16岁以上。2.伤前生活质量及活动水平。3.全身状况允许手术。4.首选钢板螺钉内固定，也可根据具体情况选择其他治疗方式。";
	private String lujing1 = "（一）适用对象。第一诊断为闭合性肱骨髁骨折（ICD-10:S42.401）行肱骨髁骨折内固定术（ICD-9-CM-3：78.52/79.11/79.31）。（二）诊断依据。根据《外科学（下册）》（8年制和7年制临床医学专用教材，人民卫生出版社，2005年8月第1版）。1.病史：外伤史。2.体格检查：患肢肿胀、疼痛、活动受限、畸形，反常活动。3.辅助检查：X线检查发现肱骨髁骨折。（三）选择治疗方案的依据。根据《外科学（下册）》（8年制和7年制教材临床医学专用，人民卫生出版社，2005年8月第1版）。1.年龄在16岁以上。2.伤前生活质量及活动水平。 3.全身状况允许手术。4.首选钢板螺钉内固定，也可根据具体情况选择其他治疗方式。";
	private String lujing2 = "（一）适用对象。第一诊断为胫骨平台骨折（ICD-10：S82.10）行切开复位内固定术(ICD-9-CM-3:79.36)（二）诊断依据。根据《临床诊疗指南-骨科学分册》（中华医学会编著，人民卫生出版社），《外科学（下册）》（8年制和7年制教材临床医学专用，人民卫生出版社）1.病史：外伤史。2.体检有明确体征：患侧膝关节肿胀、疼痛、活动受限。3.辅助检查：膝关节X线片显示胫骨平台骨折。（三）治疗方案的选择及依据。根据《临床诊疗指南-骨科学分册》（中华医学会编著，人民卫生出版社），《外科学（下册）》（8年制和7年制教材临床医学专用，人民卫生出版社）1.明显移位的关节内骨折。2.无手术禁忌证。";
	private String lujing3 = "（一）适用对象。第一诊断为闭合性尺骨鹰嘴骨折（ICD-10:S52.001）行尺骨鹰嘴骨折内固定术（ICD-9-CM-3：78.53/79.12/79.32）。（二）诊断依据。根据《外科学（下册）》（8年制和7年制临床医学专用教材，人民卫生出版社，2005年8月第1版）。1.病史：外伤史。2.体格检查：患肢肿胀、疼痛、活动受限、畸形，反常活动。3.辅助检查：X线检查发现尺骨鹰嘴骨折。（三）选择治疗方案的依据。根据《外科学（下册）》（8年制和7年制临床医学专用教材，人民卫生出版社，2005年8月第1版）。1.年龄在16岁以上。2.伤前生活质量及活动水平。 3.全身状况允许手术。4.首选克氏针张力带固定，也可根据具体情况选择其他治疗方式。";
	private String lujing4 = "（一）适用对象。第一诊断为闭合性尺桡骨干骨折（ICD-10:S52.401）行尺桡骨干骨折内固定术（ICD-9-CM-3：78.53/79.12/79.32）。（二）诊断依据。根据《外科学（下册）》（8年制和7年制临床医学专用教材，人民卫生出版社，2005年8月第1版）。1.病史：外伤史。2.体格检查：患肢肿胀、疼痛、活动受限、畸形，反常活动。3.辅助检查：X线检查发现尺桡骨干骨折。（三）选择治疗方案的依据。根据《外科学（下册）》（8年制和7年制教材临床医学专用，人民卫生出版社，2005年8月第1版）。1.年龄在16岁以上。2.伤前生活质量及活动水平。3.全身状况允许手术。4.首选钢板螺钉内固定，也可根据具体情况选择其他治疗方式。";
	private String lujing5 = "（一）适用对象。第一诊断为闭合性股骨髁骨折（ICD-10：S72.401）行股骨髁骨折内固定术（ICD-9-CM-3：78.55/79.15/79.35）。（二）诊断依据。根据《外科学（下册）》（8年制和7年制临床医学专用教材，人民卫生出版社，2005年8月第1版）。1.病史：外伤史。2.体格检查：患肢肿胀、疼痛、活动受限、畸形、反常活动。3.辅助检查：X线检查发现股骨髁骨折。（三）选择治疗方案的依据。根据《外科学（下册）》（8年制和7年制临床医学专用教材，人民卫生出版社，2005年8月第1版）。1.年龄在16岁以上。2.伤前生活质量及活动水平。 3.全身状况允许手术。4.首选钢板螺钉内固定，也可根据具体情况选择其他治疗方式。";
	private String lujing6 = "（一）适用对象。第一诊断为闭合性髌骨骨折（ICD-10：S82.001）行髌骨骨折内固定术（ICD-9-CM-3：78.56/79.1901/79.3901/）。（二）诊断依据。根据《外科学（下册）》（8年制和7年制临床医学专用教材，人民卫生出版社，2005年8月第1版）。1.病史：外伤史。2.体格检查：患膝肿胀、疼痛、活动受限。3.辅助检查：X线检查发现髌骨骨折。（三）选择治疗方案的依据。根据《外科学（下册）》（8年制和7年制教材临床医学专用，人民卫生出版社，2005年8月第1版）。1.年龄在16岁以上。2.伤前生活质量及活动水平。 3.全身状况允许手术。4.首选克氏针张力带固定，也可根据具体情况选择其他治疗方式。";
	private String lujing7 = "（一）适用对象。第一诊断为闭合性胫腓骨干骨折（ICD-10：S82.201）行胫腓骨干骨折内固定术（ICD-9-CM-3：78.57/79.16/79.36）。（二）诊断依据。根据《外科学（下册）》（8年制和7年制临床医学专用教材，人民卫生出版社，2005年8月第1版）。1.病史：外伤史。2.体格检查：患肢肿胀、疼痛、活动受限、畸形、反常活动。3.辅助检查：X线检查发现胫腓骨干骨折。（三）选择治疗方案的依据。根据《外科学（下册）》（8年制和7年制临床医学专用教材，人民卫生出版社，2005年8月第1版）。1.年龄在16岁以上。2.伤前生活质量及活动水平。 3.全身状况允许手术。4.首选钢板螺钉内固定，也可根据具体情况选择其他治疗方式。";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_watchlibtemp);
		initViews();
		initDates();
		initEvents();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		title = (TextView) this.findViewById(R.id.temp_title);
		content = (TextView) this.findViewById(R.id.temp_text);
	}

	private void initDates() {
		// TODO Auto-generated method stub
		String str_title = getIntent().getStringExtra("title");
		title.setText(str_title);
		String chose = "";
		int index = getIntent().getIntExtra("index", 0);
		int content = getIntent().getIntExtra("content", 0);
		if (index == 0) {
			if (content == 0)
				chose = xinwen0;
			else if (content == 1)
				chose = xinwen1;
			else if (content == 2)
				chose = xinwen2;
			else if (content == 3)
				chose = xinwen3;
			else if (content == 4)
				chose = xinwen4;
			else if (content == 5)
				chose = xinwen5;
			else if (content == 6)
				chose = xinwen6;
			else if (content == 7)
				chose = xinwen7;
		} else if (index == 1) {
			chose = gongshi0;
		} else if (index == 2) {
			if (content == 0)
				chose = lujing0;
			else if (content == 1)
				chose = lujing1;
			else if (content == 2)
				chose = lujing2;
			else if (content == 3)
				chose = lujing3;
			else if (content == 4)
				chose = lujing4;
			else if (content == 5)
				chose = lujing5;
			else if (content == 6)
				chose = lujing6;
			else if (content == 7)
				chose = lujing7;
		}
		// title.setText(getIntent().getStringExtra("title"));
		this.content.setText(chose);
	}

	private void initEvents() {
		// TODO Auto-generated method stub

	}

}
