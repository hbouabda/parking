package com.example.parking.service;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActionPlanningServiceTest {

    /*private static final int PROGRAM_ID = 1;
    private static final int SUPERVISION_ACTION_ID = 1;
    private static final int ACTION_PLANNING_ID = 1;

    @InjectMocks
    ActionPlanningServiceImpl actionPlanningService;

    @Mock
    private ActionPlanningRepository actionPlanningRepository;

    @Mock
    SupervisionActionRepository supervisionActionRepository;

    @Mock
    StatActionPlanningService statService;

    @Mock
    ProgramRepository programRepository;

    @Mock
    ObservableStatService observableStatService;

    @Mock
    ObservablePlanningStatService observablePlanningStatService;

    @Mock
    ObservableStatRepository observableStatRepository;

    @Mock
    ObservablePlanningStatRepository observablePlanningStatRepository;

    @Mock
    ActionPlanningGroupRepository actionPlanningGroupRepository;

    @Mock
    DateService dateService;

    @Mock
    private ActionPlanningValidator actionPlanningValidator;

    @Mock
    private ProgramValidator programValidator;

    ActionPlanningListItem actionPlanningListItem;

    SupervisionAction supervisionAction;

    Program program;

    LocalDate programStartDate;

    LocalDate programEndDate;

    LocalDate itemStartDate;

    ActionPlanning actionPlanning;

    @Before
    public void init() throws Exception {

        programStartDate = LocalDate.of(2020, 12, 01);
        programEndDate = LocalDate.of(2021, 01, 01);
        itemStartDate = LocalDate.of(2020, 12, 30);
        actionPlanningListItem = new ActionPlanningListItem(ACTION_PLANNING_ID, SUPERVISION_ACTION_ID, itemStartDate, false, null, null, 1, 0);

        program = new Program();
        program.setId(PROGRAM_ID);
        program.setStartDate(programStartDate);
        program.setEndDate(programEndDate);

        supervisionAction = new SupervisionAction();
        supervisionAction.setId(SUPERVISION_ACTION_ID);
        supervisionAction.setSupervisionProgram(program);
        supervisionAction.setObjective(0);
        Set<SupervisionActionObservable> supervisionActionObservables = new HashSet<>();
        supervisionAction.setSupervisionActionObservables(supervisionActionObservables);

        actionPlanning = new ActionPlanning();
        actionPlanning.setId(ACTION_PLANNING_ID);
        actionPlanning.setStartDate(LocalDate.of(2020, 12, 28));
        actionPlanning.setAction(supervisionAction);
    }

    @Test
    public void testGetActionsPlanning() {

        ActionPlanningListItem actionPlanningListItem1 = new ActionPlanningListItem(1, 1, null, true, null, null, 1, 1);
        ActionPlanningListItem actionPlanningListItem2 = new ActionPlanningListItem(1, 1, null, true, null, null, 1, 1);

        when(actionPlanningRepository.getActionsForPlanning(PROGRAM_ID)).thenReturn(asList(actionPlanningListItem1, actionPlanningListItem2));

        //when
        List<ActionPlanningListItem> actionItems = actionPlanningService.getActionsPlanning(PROGRAM_ID);


        assertThat(actionItems)
                .contains(actionPlanningListItem1, actionPlanningListItem2);
        verify(actionPlanningRepository).getActionsForPlanning(PROGRAM_ID);

    }

    @Test
    public void testCreateActionsPlanning() {

        //given
        given(supervisionActionRepository.findOne(SUPERVISION_ACTION_ID)).willReturn(supervisionAction);
        given(dateService.getMonday(programStartDate)).willReturn(LocalDate.of(2020, 11, 30));
        given(dateService.getMonday(itemStartDate)).willReturn(LocalDate.of(2020, 12, 28));
        given(programRepository.findBySupervisionActionsActionPlanningsId(ACTION_PLANNING_ID)).willReturn(program);

        Map<Integer, Observable> actionObservablesMap = new HashMap<>();
        given(observableStatService.findAll(PROGRAM_ID, new HashSet<>(actionObservablesMap.values()))).willReturn(new ArrayList<>());

        Set<LocalDate> dates = new HashSet<>();
        given(observablePlanningStatRepository.findByActionIdAndPlanningStartDateIsNullOrPlanningStartDateIn(SUPERVISION_ACTION_ID, dates)).willReturn(new ArrayList<>());

        //when
        ActionPlanningResult result = actionPlanningService.createActionPlanning(actionPlanningListItem);

        ActionPlanningListItem actionPlanningListItem = new ActionPlanningListItem(null, SUPERVISION_ACTION_ID, LocalDate.of(2020, 12, 28), false, null, null, null, 0);
        List<ActionPlanningListItem> actionPlanningListItems = result.getItemsToAdd();

        ArgumentCaptor<ActionPlanning> captorActionPlanning = ArgumentCaptor.forClass(ActionPlanning.class);
        //then
        assertThat(actionPlanningListItems)
                .usingElementComparatorIgnoringFields("startDate")
                .containsExactlyInAnyOrder(actionPlanningListItem);

        verify(actionPlanningRepository).save(captorActionPlanning.capture());
        ActionPlanning actionPlanning = captorActionPlanning.getValue();

        verify(statService).updateForCreation(supervisionAction, Collections.singleton(actionPlanning.getStartDate()));

        ActionPlanning actionPlanningExpected = new ActionPlanning();
        actionPlanningExpected.setAction(supervisionAction);
        actionPlanningExpected.setIsDone(false);

        assertThat(actionPlanning.getAction()).isSameAs(supervisionAction);
        assertThat(actionPlanning.getIsDone()).isFalse();
        assertThat(actionPlanning.getPlanningGroup()).isNull();


    }

    @Test
    public void failCreatingActionsPlanningWhenSupervisionActionNotFound() {

        given(supervisionActionRepository.findOne(SUPERVISION_ACTION_ID)).willReturn(null);

        //then
        exception.expect(NotFoundException.class);
        exception.expectMessage(MessageFormat.format("La supervision action {0} n''a pas été trouvée", SUPERVISION_ACTION_ID));

        //when
        actionPlanningService.createActionPlanning(actionPlanningListItem);

    }

    @Test
    public void testUpdateActionsPlanning() {

        actionPlanningListItem.setStartDate(LocalDate.of(2020, 12, 04));
        //given
        given(actionPlanningRepository.findOne(ACTION_PLANNING_ID)).willReturn(actionPlanning);
        given(supervisionActionRepository.findOne(SUPERVISION_ACTION_ID)).willReturn(supervisionAction);
        given(dateService.getMonday(LocalDate.of(2020, 12, 04))).willReturn(LocalDate.of(2020, 11, 30));
        given(dateService.getMonday(LocalDate.of(2020, 11, 30))).willReturn(LocalDate.of(2020, 11, 30));
        given(dateService.getMonday(programStartDate)).willReturn(LocalDate.of(2020, 11, 30));
        given(dateService.getMonday(itemStartDate)).willReturn(LocalDate.of(2020, 12, 28));
        given(programRepository.findBySupervisionActionsActionPlanningsId(ACTION_PLANNING_ID)).willReturn(program);

        Map<Integer, Observable> actionObservablesMap = new HashMap<>();
        given(observableStatService.findAll(PROGRAM_ID, new HashSet<>(actionObservablesMap.values()))).willReturn(new ArrayList<>());

        Set<LocalDate> dates = new HashSet<>();
        given(observablePlanningStatRepository.findByActionIdAndPlanningStartDateIsNullOrPlanningStartDateIn(SUPERVISION_ACTION_ID, dates)).willReturn(new ArrayList<>());

        //when
        ActionPlanningResult result = actionPlanningService.updateActionPlanning(actionPlanningListItem);

        ActionPlanningListItem actionPlanningListItem = new ActionPlanningListItem(null, SUPERVISION_ACTION_ID, LocalDate.of(2020, 11, 30), false, null, null, null, 0);
        List<ActionPlanningListItem> actionPlanningListItems = result.getItemsToAdd();

        ArgumentCaptor<ActionPlanning> captorActionPlanning = ArgumentCaptor.forClass(ActionPlanning.class);
        //then
        assertThat(actionPlanningListItems)
                .containsExactlyInAnyOrder(actionPlanningListItem);

        verify(statService).updateForDeletion(supervisionAction, Collections.singleton(actionPlanning.getStartDate()), false);
        verify(actionPlanningRepository).save(captorActionPlanning.capture());

        ActionPlanning actionPlanningCaptor = captorActionPlanning.getValue();

        ActionPlanning actionPlanningExpected = new ActionPlanning();
        actionPlanningExpected.setAction(supervisionAction);
        actionPlanningExpected.setIsDone(false);

        assertThat(actionPlanningCaptor.getAction()).isSameAs(supervisionAction);
        assertThat(actionPlanningCaptor.getIsDone()).isFalse();
        assertThat(actionPlanningCaptor.getPlanningGroup()).isNull();


    }

    @Test
    public void failCreatingUpdatePlanningWhenSupervisionActionNotFound() {

        //then
        exception.expect(NotFoundException.class);
        exception.expectMessage(MessageFormat.format("l''action planning {0} n''a pas été trouvée", ACTION_PLANNING_ID));

        //when
        actionPlanningService.updateActionPlanning(actionPlanningListItem);

    }

    @Test
    public void testDeletionActionPlanning() {
        //given
        given(actionPlanningRepository.findOne(ACTION_PLANNING_ID)).willReturn(actionPlanning);
        //when
        ActionPlanningResult delete = actionPlanningService.delete(ACTION_PLANNING_ID);

        //then
        assertThat(delete.getItemsToDelete())
                .containsExactlyInAnyOrder(ACTION_PLANNING_ID);
        verify(statService).updateForDeletion(supervisionAction, Collections.singleton(actionPlanning.getStartDate()), true);
        verify(actionPlanningRepository).delete(ACTION_PLANNING_ID);
    }

    @Test
    public void testDeletionActionPlanning_withPlanningGroup() {
        //given
        ActionPlanningGroup actionPlanningGroup = new ActionPlanningGroup();
        actionPlanningGroup.setId(ACTION_PLANNING_ID);
        ActionPlanning actionPlanning1 = new ActionPlanning();
        ActionPlanning actionPlanning2 = new ActionPlanning();
        actionPlanning1.setId(ACTION_PLANNING_ID + 1);
        actionPlanning2.setId(ACTION_PLANNING_ID + 2);
        actionPlanningGroup.setActionPlannings(new HashSet<>(asList(actionPlanning, actionPlanning1, actionPlanning2)));
        actionPlanning.setPlanningGroup(actionPlanningGroup);

        given(actionPlanningRepository.findOne(ACTION_PLANNING_ID)).willReturn(actionPlanning);
        given(actionPlanningRepository.findByPlanningGroupId(ACTION_PLANNING_ID)).willReturn(asList(actionPlanning, actionPlanning1, actionPlanning2));

        //when
        ActionPlanningResult delete = actionPlanningService.delete(ACTION_PLANNING_ID);

        //then
        assertThat(delete.getItemsToDelete())
                .containsExactlyInAnyOrder(ACTION_PLANNING_ID, ACTION_PLANNING_ID + 1, ACTION_PLANNING_ID + 2);
        verify(actionPlanningRepository).findOne(ACTION_PLANNING_ID);
        verify(actionPlanningRepository).findByPlanningGroupId(ACTION_PLANNING_ID);
        verify(actionPlanningRepository).delete(ACTION_PLANNING_ID);
        verify(actionPlanningRepository).delete(ACTION_PLANNING_ID + 1);
        verify(actionPlanningRepository).delete(ACTION_PLANNING_ID + 2);
        verify(actionPlanningGroupRepository).delete(ACTION_PLANNING_ID);
        verify(statService).updateForDeletion(same(supervisionAction), anySetOf(LocalDate.class), eq(true));
    }

    @Test
    public void testDeletionActionPlanning_withPlanningGroup_markDone() {
        //given
        ActionPlanningGroup actionPlanningGroup = new ActionPlanningGroup();
        actionPlanningGroup.setId(ACTION_PLANNING_ID);
        actionPlanning.setIsDone(false);
        ActionPlanning actionPlanning1 = new ActionPlanning();
        ActionPlanning actionPlanning2 = new ActionPlanning();
        actionPlanning1.setId(ACTION_PLANNING_ID + 1);
        actionPlanning2.setId(ACTION_PLANNING_ID + 2);
        actionPlanning1.setIsDone(false);
        actionPlanning2.setIsDone(true);
        actionPlanningGroup.setActionPlannings(new HashSet<>(asList(actionPlanning, actionPlanning1, actionPlanning2)));
        actionPlanning.setPlanningGroup(actionPlanningGroup);

        ObservablePlanningStat observablePlanningStat1 = new ObservablePlanningStat();
        LocalDate planningStartDate = LocalDate.now();
        observablePlanningStat1.setPlanningStartDate(planningStartDate);
        observablePlanningStat1.setObjective(2);
        observablePlanningStat1.setExpectedCount(10);
        ObservablePlanningStat observablePlanningStat2 = new ObservablePlanningStat();
        observablePlanningStat2.setPlanningStartDate(planningStartDate);
        observablePlanningStat2.setObjective(1);
        observablePlanningStat2.setExpectedCount(3);

        given(actionPlanningRepository.findOne(ACTION_PLANNING_ID)).willReturn(actionPlanning);
        given(observablePlanningStatRepository.findAllByActionId(SUPERVISION_ACTION_ID)).willReturn(asList(observablePlanningStat1, observablePlanningStat2));
        given(actionPlanningRepository.findByPlanningGroupId(ACTION_PLANNING_ID)).willReturn(asList(actionPlanning, actionPlanning1, actionPlanning2));
        given(actionPlanningRepository.findByActionIdAndStartDate(SUPERVISION_ACTION_ID, planningStartDate)).willReturn(asList(actionPlanning, actionPlanning1, actionPlanning2));
        //when
        ActionPlanningResult delete = actionPlanningService.delete(ACTION_PLANNING_ID);

        //then
        assertThat(delete.getItemsToDelete())
                .containsExactlyInAnyOrder(ACTION_PLANNING_ID, ACTION_PLANNING_ID + 1, ACTION_PLANNING_ID + 2);
        verify(actionPlanningRepository).findOne(ACTION_PLANNING_ID);
        verify(actionPlanningRepository).findByPlanningGroupId(ACTION_PLANNING_ID);
        verify(actionPlanningRepository).delete(ACTION_PLANNING_ID);
        verify(actionPlanningRepository).delete(ACTION_PLANNING_ID + 1);
        verify(actionPlanningRepository).delete(ACTION_PLANNING_ID + 2);
        verify(actionPlanningGroupRepository).delete(ACTION_PLANNING_ID);
        verify(statService).updateForDeletion(same(supervisionAction), anySetOf(LocalDate.class), eq(true));
    }*/
}

