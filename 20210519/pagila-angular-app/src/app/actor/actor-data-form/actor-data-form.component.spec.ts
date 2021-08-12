import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActorDataFormComponent } from './actor-data-form.component';

describe('ActorDataFormComponent', () => {
  let component: ActorDataFormComponent;
  let fixture: ComponentFixture<ActorDataFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActorDataFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActorDataFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
