/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('HOSPITALS', {
    hospital_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    hospital_code: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    hospital_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'HOSPITAL_TYPE',
        key: 'hospital_type_id'
      }
    },
    street_adress: {
      type: DataTypes.STRING,
      allowNull: false
    },
    establishement_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    total_score: {
      type: DataTypes.BIGINT,
      allowNull: false
    },
    total_vote: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    overall_score: {
      type: "DOUBLE(10,5)",
      allowNull: false
    },
    isActive: {
      type: DataTypes.INTEGER(1),
      allowNull: false
    },
    website: {
      type: DataTypes.STRING,
      allowNull: false
    },
    phone_number: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'HOSPITALS'
  });
};
