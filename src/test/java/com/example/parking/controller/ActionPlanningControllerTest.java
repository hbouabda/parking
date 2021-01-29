package com.example.parking.controller;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActionPlanningControllerTest {

    /*@InjectMocks
    private ActionPlanningController actionPlanningController;

    @Mock
    private ActionPlanningService actionPlanningService;

    @Mock
    private ActionPlanningListItemMapper actionPlanningListItemMapper;

    @Mock
    private ActionPlanningResultMapper actionPlanningResultMapper;

    private final Integer PROGRAM_ID = 1;

    private final List<ActionPlanningListItem> models = mockCollection(ActionPlanningListItem.class, 3, ArrayList::new);
    private final List<ActionPlanningListItemDto> dtos = new ArrayList<>();

    private final ActionPlanningListItem modelListItem = mock(ActionPlanningListItem.class);
    private final ActionPlanningListItemDto dtoListItem = new ActionPlanningListItemDto(null, null, null, null, null, null, null, 0);

    private final ActionPlanningResult modelPlanningResult = mock(ActionPlanningResult.class);
    private final ActionPlanningResultDto dtoPlanningResult = new ActionPlanningResultDto(null, null);


    @Test
    public void testGetActionsPlanning() {
        //given
        List<ActionPlanningListItem> models = mockCollection(ActionPlanningListItem.class, 3, ArrayList::new);

        ActionPlanningListItemDto itemDto1 = new ActionPlanningListItemDto(1, null, null, null, null, null, 0, 0);
        ActionPlanningListItemDto itemDto2 = new ActionPlanningListItemDto(2, null, null, null, null, null, 0, 0);
        ActionPlanningListItemDto itemDto3 = new ActionPlanningListItemDto(3, null, null, null, null, null, 0, 0);

        List<ActionPlanningListItemDto> responseDto = Arrays.asList(itemDto1, itemDto2, itemDto3);
        given(actionPlanningService.getActionsPlanning(PROGRAM_ID)).willReturn(models);
        given(actionPlanningListItemMapper.toDtos(models)).willReturn(responseDto);

        //when
        ResponseEntity<List<ActionPlanningListItemDto>> responseEntity = actionPlanningController.getActionsPlanning(PROGRAM_ID);

        //then
        verify(actionPlanningListItemMapper).toDtos(models);
        verify(actionPlanningService).getActionsPlanning(PROGRAM_ID);

        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody()).isSameAs(responseDto);
    }

    @Test
    public void testCreateActionsPlanning() {
        //given
        given(actionPlanningListItemMapper.toModel(dtoListItem)).willReturn(modelListItem);
        given(actionPlanningResultMapper.toDto(modelPlanningResult)).willReturn(dtoPlanningResult);
        given(actionPlanningService.createActionPlanning(modelListItem)).willReturn(modelPlanningResult);

        //when
        ResponseEntity<ActionPlanningResultDto> response = actionPlanningController.createActionPlanning(dtoListItem);

        //then
        verify(actionPlanningListItemMapper).toModel(dtoListItem);
        verify(actionPlanningResultMapper).toDto(modelPlanningResult);
        verify(actionPlanningService).createActionPlanning(modelListItem);
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isSameAs(dtoPlanningResult);
    }

    @Test
    public void testUpdateActionsPlanning() {
        //given
        given(actionPlanningListItemMapper.toModel(dtoListItem)).willReturn(modelListItem);
        given(actionPlanningResultMapper.toDto(modelPlanningResult)).willReturn(dtoPlanningResult);
        given(actionPlanningService.createActionPlanning(modelListItem)).willReturn(modelPlanningResult);

        //when
        ResponseEntity<ActionPlanningResultDto> response = actionPlanningController.createActionPlanning(dtoListItem);

        //then
        verify(actionPlanningListItemMapper).toModel(dtoListItem);
        verify(actionPlanningResultMapper).toDto(modelPlanningResult);
        verify(actionPlanningService).createActionPlanning(modelListItem);
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isSameAs(dtoPlanningResult);
    }*/
}
